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
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import in.thirumal.exception.ResourceNotFoundException;

/**
 * @author Thirumal
 *
 */
@Service
public class RegisterClientService {

	Logger logger = LoggerFactory.getLogger(RegisterClientService.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	RegisteredClientRepository registeredClientRepository;
	
	
	public RegisteredClient register(RegisteredClient registeredClient) {
		logger.debug("Registering the client {}", registeredClient);
		registeredClientRepository.save(registeredClient);
		return registeredClient;
	}
	
	public RegisteredClient get(String id) {
		logger.debug("Finding registered client.... {}", id);
		JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
		RegisteredClient registeredClient = registeredClientRepository.findById(id);
		logger.debug("Client detail : {}", registeredClient);
		if (registeredClient == null) {
			throw new ResourceNotFoundException("The requested client is not found");
		}
		return registeredClient;
	}
	
	
}
