/**
 * 
 */
package in.thirumal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author திருமால்
 *
 */
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaConfig {
	
	private String site;
    private String secret;
    
  //reCAPTCHA V3
    private String siteV3;
    private String secretV3;
    private float threshold;
    
	public CaptchaConfig() {
	}

	public CaptchaConfig(String site, String secret) {
		this.site = site;
		this.secret = secret;
	}
	
	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the siteV3
	 */
	public String getSiteV3() {
		return siteV3;
	}

	/**
	 * @param siteV3 the siteV3 to set
	 */
	public void setSiteV3(String siteV3) {
		this.siteV3 = siteV3;
	}

	/**
	 * @return the secretV3
	 */
	public String getSecretV3() {
		return secretV3;
	}

	/**
	 * @param secretV3 the secretV3 to set
	 */
	public void setSecretV3(String secretV3) {
		this.secretV3 = secretV3;
	}

	/**
	 * @return the threshold
	 */
	public float getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "CaptchaConfig [site=" + site + ", secret=" + secret + ", siteV3=" + siteV3 + ", secretV3=" + secretV3
				+ ", threshold=" + threshold + "]";
	}
    
}
