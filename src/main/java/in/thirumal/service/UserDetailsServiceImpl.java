/**
 * 
 */
package in.thirumal.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.thirumal.exception.UnAuthorizedException;
import in.thirumal.model.Contact;
import in.thirumal.model.LoginUser;
import in.thirumal.model.Password;
import in.thirumal.repository.ContactRepository;
import in.thirumal.repository.LoginUserRepository;
import in.thirumal.repository.PasswordRepository;

/**
 * @author Thirumal
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private LoginUserRepository loginUserRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private PasswordRepository passwordRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("The user {} accessing at {}", username, LocalDateTime.now());
		Contact contact = contactRepository.findActiveLoginIdByLoginId(username);
		logger.debug("Login {}", contact);
		if (contact == null) {
			String errorMessage = "The requested username : " + username + " is not available in the system";
			logger.debug(errorMessage);
			throw new UnAuthorizedException(errorMessage);
		}
		// Login User
		LoginUser loginUser = loginUserRepository.findById(contact.getLoginUserId());
		logger.debug("The login user is {}", loginUser);
		if (loginUser == null || loginUser.getLoginUuid() == null) {
			throw new UnAuthorizedException("Login id is not available");
		}
		// Password
		//TODO Verify Expire Date
		Password password = passwordRepository.findByLoginUserId(contact.getLoginUserId());
		if (password == null) {
			throw new UnAuthorizedException("Password is not set/found.");
		}		
		//TODO ROLES
		return User.withUsername(loginUser.getLoginUuid().toString()).password(password.getSecretKey()).roles("ADMIN").build();
	}

}
