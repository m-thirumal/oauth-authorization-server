package in.thirumal.repository;

import java.util.UUID;
import org.springframework.lang.Nullable;
import in.thirumal.model.LoginUser;
/**
 * @author Thirumal
 *
 */
public interface LoginUserRepository {

	/**
	 * Saves the Login user (Resource Owner).
	 *
	 *
	 * @param loginUser the {@link LoginUser}
	 */
	Long save(LoginUser loginUser);

	/**
	 * Returns the login user (Resource Owner) identified by the provided {@code id},
	 * or {@code null} if not found.
	 *
	 * @param id the login user identifier
	 * @return the {@link LoginUser} if found, otherwise {@code null}
	 */
	@Nullable
	LoginUser findById(Long id);
	
	
	/**
	 * Returns the login user (Resource Owner) identified by the provided {@code uuid},
	 * or {@code null} if not found.
	 *
	 * @param uuid the login user identifier
	 * @return the {@link LoginUser} if found, otherwise {@code null}
	 */
	@Nullable
	LoginUser findByUuid(UUID uuid);

}
