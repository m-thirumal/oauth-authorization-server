/**
 * 
 */
package in.thirumal.repository;

import java.util.List;
import java.util.Set;

import in.thirumal.model.Contact;

/**
 * @author Thirumal
 *
 */
public interface ContactRepository {

	Long save(Contact contact);
	
	int[] saveAll(List<Contact> contacts);
	
	Contact findById(Long id);
	
	Contact findByLoginUserId(Long id);
	
	List<Contact> findAllByLoginUserId(Long id);

	Contact findActiveLoginIdByLoginId(String username);

	List<Contact> findByLoginId(Set<String> of);
	/**
	 * Verify contact with OTP
	 * @param contactId
	 */
	int verify(Long contactId);
	
}
