/**
 * 
 */
package in.thirumal.repository;

import in.thirumal.model.LoginUserName;

/**
 * @author Thirumal
 *
 */
public interface LoginUserNameRepository {

	Long save(LoginUserName loginUserName);
	
	LoginUserName findById(Long id);
	
	LoginUserName findByLoginUserId(Long id);
	
}
