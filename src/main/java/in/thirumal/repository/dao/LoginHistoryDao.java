/**
 * 
 */
package in.thirumal.repository.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.thirumal.model.LoginHistory;
import in.thirumal.repository.LoginHistoryRepository;

/**
 * @author Thirumal
 *
 */
@Repository
public class LoginHistoryDao implements LoginHistoryRepository {

	@Override
	public Long save(LoginHistory loginHistory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long saveLogout(Long loginId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoginHistory> list(Long loginId) {
		// TODO Auto-generated method stub
		return null;
	}

}
