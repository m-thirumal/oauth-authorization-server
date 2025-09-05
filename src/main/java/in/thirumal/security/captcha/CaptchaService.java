package in.thirumal.security.captcha;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import in.thirumal.config.CaptchaConfig;
import in.thirumal.exception.BadRequestException;
import in.thirumal.model.GoogleResponse;
import jakarta.servlet.http.HttpServletRequest;

@Service("captchaService")
public class CaptchaService implements ICaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CaptchaConfig captchaConfig;
    @Autowired
    private ReCaptchaAttemptService reCaptchaAttemptService;

    private final WebClient webClient;

    @Value("${google.recaptcha.key.secret}")
    private String recaptchaSecret;

    public CaptchaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public void processResponse(String response) {
        LOGGER.debug("Attempting to validate response {}", response);

        if (reCaptchaAttemptService.isBlocked(getClientIP())) {
            throw new BadRequestException("Client exceeded maximum number of failed attempts");
        }

        if (!responseSanityCheck(response)) {
            throw new BadRequestException("Response contains invalid characters");
        }

        URI verifyUri = URI.create(String.format(
                "%s?secret=%s&response=%s&remoteip=%s",
                GOOGLE_RECAPTCHA_VERIFY_URL, getReCaptchaSecret(), response, getClientIP()
        ));

        try {
            GoogleResponse googleResponse = webClient.get()
                    .uri(verifyUri)
                    .retrieve()
                    .bodyToMono(GoogleResponse.class)
                    .block(); // blocking call for simplicity

            LOGGER.debug("Google's response: {} ", googleResponse);

            if (googleResponse == null || !googleResponse.isSuccess()) {
                if (googleResponse != null && googleResponse.hasClientError()) {
                    reCaptchaAttemptService.reCaptchaFailed(getClientIP());
                }
                throw new BadRequestException("reCaptcha was not validated");
            }

        } catch (Exception e) {
            throw new BadRequestException("Registration unavailable at this time. Please try again later.", e);
        }

        reCaptchaAttemptService.reCaptchaSucceeded(getClientIP());
    }

    private boolean responseSanityCheck(final String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

    @Retryable(retryFor = { Exception.class }, maxAttempts = 2, backoff = @Backoff(delay = 5000))
    public String verifyRecaptcha(String ip, String recaptchaResponse) {

        URI uri = URI.create(String.format(
                "%s?secret=%s&response=%s&remoteip=%s",
                GOOGLE_RECAPTCHA_VERIFY_URL, recaptchaSecret, recaptchaResponse, ip
        ));

        @SuppressWarnings("unchecked")
        Map<String, Object> responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        boolean recaptchaSuccess = responseBody != null && Boolean.TRUE.equals(responseBody.get("success"));

        if (!recaptchaSuccess) {
            if (responseBody == null) {
                LOGGER.error("Recaptcha response is null");
                throw new BadRequestException("Not able to verify reCaptcha, please try again later");
            }

            @SuppressWarnings("unchecked")
            List<String> errorCodes = (List<String>) responseBody.get("error-codes");
            String errorMessage = String.join(",", errorCodes);
            LOGGER.error("Google ReCaptcha: {}", errorMessage);
            if ("timeout-or-duplicate".equals(errorMessage)) {
                errorMessage = "ReCaptcha Expired ⌛, Try Again!";
            }
            throw new BadRequestException(errorMessage);
        }

        return "";
    }

    @Override
    public String getReCaptchaSite() {
        return captchaConfig.getSite();
    }

    @Override
    public String getReCaptchaSecret() {
        return captchaConfig.getSecret();
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
