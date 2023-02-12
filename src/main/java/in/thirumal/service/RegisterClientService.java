/**
 * 
 */
package in.thirumal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import in.thirumal.model.Oauth2RegisteredClient;

/**
 * @author Thirumal
 *
 */
@Service
public class RegisterClientService {

	Logger logger = LoggerFactory.getLogger(RegisterClientService.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public Oauth2RegisteredClient register(Oauth2RegisteredClient oauth2RegisteredClient) {
		logger.debug("Registering the client {}", oauth2RegisteredClient);
		return oauth2RegisteredClient;
	}
	
	
	public RegisteredClient get(String id) {
		logger.debug("Finding registered client.... {}", id);
		 JdbcRegisteredClientRepository registeredClientRepository =
       	      new JdbcRegisteredClientRepository(jdbcTemplate);
		return registeredClientRepository.findById(id);
	}
	
	
}
