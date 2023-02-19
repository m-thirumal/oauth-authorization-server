package in.thirumal.repository.dao;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import in.thirumal.model.LoginUser;
import in.thirumal.repository.LoginUserRepository;

/**
 * 
 */

/**
 * @author Thirumal
 *
 */
@Repository
public class JdbcLoginUser implements LoginUserRepository {

	@Override
	public void save(LoginUser loginUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public LoginUser findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginUser findByUuid(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
