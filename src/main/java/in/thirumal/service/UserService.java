/**
 * 
 */
package in.thirumal.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thirumal.dao.JdbcLoginUser;
import in.thirumal.model.UserResource;

/**
 * @author Thirumal
 *
 */
@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private JdbcLoginUser jdbcLoginUser;
	
	private void createAccount() {
		//TOD Create new account 
	}

	private boolean changePassword() {
		return false;
	}

	public UserResource createAccount(UserResource userResource) {
		logger.debug("Creating new user.... {}", userResource);
		
		return userResource;
	}

	public UserResource get(UUID loginUuid) {
		logger.debug("Getting the user {}", loginUuid);
		return null;
	}
	
	
}
