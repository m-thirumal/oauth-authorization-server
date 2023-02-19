package in.thirumal.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thirumal.model.GenericCd;
import in.thirumal.model.UserResource;
import in.thirumal.repository.GenericCdRepository;
import in.thirumal.repository.dao.JdbcLoginUser;

/**
 * @author Thirumal
 *
 */
@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private GenericCdRepository genericCdRepository;
	
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
		List<GenericCd> genericCds = genericCdRepository.list("contact", 1L);
		for (GenericCd genericCd : genericCds) {
			System.out.println(genericCd);
		}
		validateEmailAndPhoneNumber(userResource);
		return userResource;
	}

	private void validateEmailAndPhoneNumber(UserResource userResource) {
		logger.debug("E-mail and phone number validation");
		// E-mail validation
		//Phone Number validation
		// Pattern pattern = Pattern.compile(patterns);
	}

	public UserResource get(UUID loginUuid) {
		logger.debug("Getting the user {}", loginUuid);
		return null;
	}
	
	
}
