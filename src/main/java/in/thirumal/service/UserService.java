/**
 * 
 */
package in.thirumal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thirumal.dao.JdbcLoginUser;

/**
 * @author Thirumal
 *
 */
@Service
public class UserService {
	
	@Autowired
	private JdbcLoginUser jdbcLoginUser;
	
	private void createAccount() {
		//TOD Create new account 
	}

	private boolean changePassword() {
		return false;
	}
	
	
}
