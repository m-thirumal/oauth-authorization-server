/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

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
	
}
