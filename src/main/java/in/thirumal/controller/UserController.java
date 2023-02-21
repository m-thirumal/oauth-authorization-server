/**
 * 
 */
package in.thirumal.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.model.Login;
import in.thirumal.model.UserResource;
import in.thirumal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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
	
	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> login(@Valid @RequestBody Login login,// @RequestParam(name="recaptcha") String recaptchaResponse,
			HttpServletRequest request) {
		logger.debug("Login ");
		//logger.debug("The current active profile is {}", activeProfile);
		/*if (!Set.of("UDEV").contains(activeProfile)) {// || !loginResource.isDesktopVersion()) {//Start of Verify reCaptcha
			String ip = request.getRemoteAddr();
			//String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(captchaVerifyMessage)) {
				Map<String, Object> response = new HashMap<>();
				response.put("message", captchaVerifyMessage);
				return ResponseEntity.badRequest()
						.body(response);
			}//End of reCaptcha
		}
		if (loginResource.getIpAddress() == null) {
			loginResource.setIpAddress(request.getRemoteAddr());
		}*/
		return new ResponseEntity<>(userService.login(login), HttpStatus.OK);
	}
	
	@GetMapping("/get-account/{loginUuid}")
	public UserResource createAccount(@PathVariable("loginUuid") UUID loginUuid) {
		return userService.get(loginUuid);
	}
		
}
