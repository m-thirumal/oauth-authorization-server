/**
 * 
 */
package in.thirumal.handler;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import in.thirumal.model.LoginHistory;
import in.thirumal.model.LoginUser;
import in.thirumal.repository.LoginHistoryRepository;
import in.thirumal.repository.LoginUserRepository;

/**
 * @author Thirumal
 * Update the number of login attempt made by user to Zero 0
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginUserRepository loginUserRepository;
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
	
	@Override@Transactional
	public void onApplicationEvent(AuthenticationSuccessEvent event) {  
		logger.debug("Login Success event : {}", event);
		String userName  = event.getAuthentication().getName();
		UUID loginId;
		try {
			loginId = UUID.fromString(userName);
		} catch (IllegalArgumentException e) {
			logger.debug("It's client id.....Ignoring.....");
			return;
		}
		LoginUser loginUser = loginUserRepository.findByUuid(loginId);
		if (Objects.isNull(loginUser)) {
			logger.error("Login User {} is not found on successful login ", userName);
		} 
		loginHistoryRepository.save(LoginHistory.builder().loginUserId(loginUser.getLoginUserId()).successLogin(true).build());
	}

	
}
