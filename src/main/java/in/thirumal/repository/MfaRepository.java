/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import in.thirumal.model.Mfa;

/**
 * @author Thirumal
 *
 */
public interface MfaRepository {

	Long save(Mfa mfa); //Enable
	
	Mfa findById(Long id);
	
	List<Mfa> findByLoginUserId(Long loginUserId);
	
	int disable(Long loginUserId);
}
