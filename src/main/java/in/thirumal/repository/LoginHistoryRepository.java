/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import in.thirumal.model.LoginHistory;

/**
 * @author Thirumal
 *
 */
public interface LoginHistoryRepository {
	
	/**
	 * Saves the Login event.
	 */
	Long save(LoginHistory loginHistory);
	
	/**
	 * Save logout time
	 * @param loginId
	 * @return
	 */
	int saveLogout(Long loginUserId);
	
	/**
	 * List login histories
	 * @param loginId
	 * @return
	 */
	List<LoginHistory> list(Long loginUserId, int limit, int offset);

}
