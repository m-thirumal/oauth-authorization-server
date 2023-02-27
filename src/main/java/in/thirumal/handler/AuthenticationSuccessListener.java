/**
 * 
 */
package in.thirumal.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author Thirumal
 * Update the number of login attempt made by user to Zero 0
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {  
		logger.debug("Login Success event : {}", event);
	}

	
}
