/**
 * 
 */
package in.thirumal.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.model.UserResource;
import in.thirumal.service.UserService;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create-account")
	public UserResource createAccount(@RequestBody UserResource userResource) {
		return userService.createAccount(userResource);
	}
	
	
	@GetMapping("/get-account/{loginUuid}")
	public UserResource createAccount(@PathVariable("loginUuid") UUID loginUuid) {
		return userService.get(loginUuid);
	}
		
}
