/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import org.springframework.lang.Nullable;

import in.thirumal.model.Password;

/**
 * @author Thirumal
 *
 */
public interface PasswordRepository {

	Long save(Password password);
	
	@Nullable
	Password findById(Long id);
	
	@Nullable
	Password findByLoginUserId(Long loginUserId);
	
	List<Password> findAllByLoginUserId(Long loginUserId);
	
	List<Password> findAllByLastNRowLoginUserId(Long loginUserId, int lastNRow);
}
