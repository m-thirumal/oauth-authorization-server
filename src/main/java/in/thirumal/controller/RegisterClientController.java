/**
 * 
 */
package in.thirumal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.model.Oauth2RegisteredClient;
import in.thirumal.service.RegisterClientService;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/client")
public class RegisterClientController {

	Logger logger = LoggerFactory.getLogger(RegisterClientController.class);
	
	@Autowired
	private RegisterClientService registerClientService; 
	
	
	@PostMapping("/register")
	public Oauth2RegisteredClient register(@RequestBody Oauth2RegisteredClient oauth2RegisteredClient) {
		return registerClientService.register(oauth2RegisteredClient);
	}
		
	@GetMapping(value = "/{id}")
	public RegisteredClient get(@PathVariable String id) {
		return registerClientService.get(id);
	}
	
}
