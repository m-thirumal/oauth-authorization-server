/**
 * 
 */
package in.thirumal.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thirumal
 * Update the number of login attempt made by user/robot
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Autowired
	//private ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		logger.debug("{}", event);
	}

}
