/**
 * 
 */
package in.thirumal.security.captcha;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import in.thirumal.config.CaptchaConfig;
import in.thirumal.exception.BadRequestException;
import in.thirumal.model.GoogleResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Thirumal
 *
 */
@Service("captchaService")
public class CaptchaService implements ICaptchaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CaptchaConfig captchaConfig;

	@Autowired
	private ReCaptchaAttemptService reCaptchaAttemptService;

	@Autowired
	private RestOperations restTemplate;
	// latest
	@Value("${google.recaptcha.key.secret}") 
	String recaptchaSecret;
	
	private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	@Autowired 
	RestTemplateBuilder restTemplateBuilder;
	

	private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
	    
	/* (non-Javadoc)
	 * @see com.enkindle.security.captcha.ICaptchaService#processResponse(java.lang.String)
	 */
	@Override
	public void processResponse(String response) {
		LOGGER.debug("Attempting to validate response {}", response);
		if (reCaptchaAttemptService.isBlocked(getClientIP())) {
			throw new BadRequestException("Client exceeded maximum number of failed attempts");
		}
		if (!responseSanityCheck(response)) {
			throw new BadRequestException("Response contains invalid characters");
		}
		final URI verifyUri = URI
				.create(String.format(GOOGLE_RECAPTCHA_VERIFY_URL + "?secret=%s&response=%s&remoteip=%s",
						getReCaptchaSecret(), response, getClientIP()));
		try {
			final GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
			LOGGER.debug("Google's response: {} ", googleResponse);
			if (googleResponse == null) {
				throw new BadRequestException("reCaptcha was not validated");
			}
			if (!googleResponse.isSuccess()) {
				if (googleResponse.hasClientError()) {
					reCaptchaAttemptService.reCaptchaFailed(getClientIP());
				}
				throw new BadRequestException("reCaptcha was not validated");
			}
		} catch (RestClientException rce) {
			throw new BadRequestException("Registration unavailable at this time.  Please try again later.", rce);
		}
		reCaptchaAttemptService.reCaptchaSucceeded(getClientIP());
	}
	
	private boolean responseSanityCheck(final String response) {
		return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
	}
	
	@Retryable(retryFor = { ResourceAccessException.class }, maxAttempts = 2, backoff = @Backoff(delay = 5000))
	public String verifyRecaptcha(String ip, String recaptchaResponse){
		Map<String, String> body = new HashMap<>();
		body.put("secret", recaptchaSecret);
		body.put("response", recaptchaResponse);
		body.put("remoteip", ip);
		LOGGER.debug("Request body for recaptcha: {}", body);
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build().postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL +
						"?secret={secret}&response={response}&remoteip={remoteip}", body, Map.class, body);
		LOGGER.debug("Response from recaptcha: {}", recaptchaResponseEntity);
		@SuppressWarnings("unchecked")
		Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
		boolean recaptchaSucess = (responseBody == null ? Boolean.FALSE : (Boolean)responseBody.get("success"));
		if (!recaptchaSucess) {
			if (responseBody == null) {
				LOGGER.error("Recaptcha status code: {}", recaptchaResponseEntity.getStatusCode());
				throw new BadRequestException("Not able to verify reCaptha, please try again later");
			}
			@SuppressWarnings({ "unchecked"})
			List<String> errorCodes = (List<String>)responseBody.get("error-codes");
			String errorMessage = String.join(",", errorCodes);
			LOGGER.error("Google ReCaptcha: {}", errorMessage);
			if ("timeout-or-duplicate".equals(errorMessage)) {
				errorMessage = "ReCaptcha Expired \u231B, Try Again!";
			}
			throw new BadRequestException(errorMessage);
		} else {
			return org.apache.commons.lang3.StringUtils.EMPTY;
		}
	}
	 
	/* (non-Javadoc)
	 * @see com.enkindle.security.captcha.ICaptchaService#getReCaptchaSite()
	 */
	@Override
	public String getReCaptchaSite() {
		return captchaConfig.getSite();
	}

	/* (non-Javadoc)
	 * @see com.enkindle.security.captcha.ICaptchaService#getReCaptchaSecret()
	 */
	@Override
	public String getReCaptchaSecret() {
		return captchaConfig.getSecret();
	}
	
	private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
