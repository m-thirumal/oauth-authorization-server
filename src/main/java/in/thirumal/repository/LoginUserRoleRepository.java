/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import org.springframework.lang.Nullable;

import in.thirumal.model.LoginUserRole;

/**
 * @author Thirumal
 *
 */
public interface LoginUserRoleRepository {

	/**
	 * Saves the Login user Role.
	 *
	 *
	 * @param loginUserRole the {@link LoginUserRole}
	 */
	Long save(LoginUserRole loginUserRole);

	/**
	 * Returns the login user Role  identified by the provided {@code id},
	 * or {@code null} if not found.
	 *
	 * @param id the login user identifier
	 * @return the {@link LoginUserRole} if found, otherwise {@code null}
	 */
	@Nullable
	LoginUserRole findById(Long id);
	
	List<LoginUserRole> findAllByLoginUserId(Long id);
	
	List<LoginUserRole> findAllByLoginUserRole(Long roleCd, int page, int limit);
	
	int revoke(Long id);
	
}
