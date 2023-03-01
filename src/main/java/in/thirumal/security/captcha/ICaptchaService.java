/**
 * 
 */
package in.thirumal.security.captcha;

/**
 * @author Thirumal
 *
 */
public interface ICaptchaService {
	
	void processResponse(final String response);

	String getReCaptchaSite();

	String getReCaptchaSecret();
	
}
