/**
 * 
 */
package in.thirumal.client;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;

/**
 * @author Thirumal
 *
 */
public class MessageServiceConfig {

	@Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
	
	@Bean
	public Request.Options notificationOptions() {
		return new Request.Options(6, TimeUnit.MINUTES, 6, TimeUnit.MINUTES, true);
	}
	
}
