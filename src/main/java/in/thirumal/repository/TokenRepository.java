/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import in.thirumal.model.Token;

/**
 * @author Thirumal
 *
 */
public interface TokenRepository {

	Long save(Token token);
	
	Token findById(Long id);
	
	Token findByContactId(Long contactId);
	
	List<Token> findAllByLoginUserId(Long contactId);
}
