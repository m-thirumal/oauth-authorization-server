/**
 * 
 */
package in.thirumal.security.captcha;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Thirumal
 */
@Component
public class RestTemplateConfig {
	
	/*
	 * Bean is required for re-captcha
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate(httpRequestFactory());
	}

	private ClientHttpRequestFactory httpRequestFactory() {
		 HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	     factory.setConnectTimeout(99999);
	     return factory;
	}

}
