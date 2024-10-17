/**
 * 
 */
package in.thirumal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.thirumal.model.ContactVerify;
import in.thirumal.model.Login;
import in.thirumal.model.PaginatedLoginHistory;
import in.thirumal.model.ResetPassword;
import in.thirumal.model.UserResource;
import in.thirumal.security.captcha.CaptchaService;
import in.thirumal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * @author Thirumal
 *
 */
@Controller
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	private CaptchaService captchaService;
	
	public UserController(UserService userService, CaptchaService captchaService) {
		super();
		this.userService = userService;
		this.captchaService = captchaService;
	}

	/**
	 * Create account without re-captcha 
	 * WARNING - Don't use it for production
	 * @param userResource
	 * @return
	 */
	@PostMapping("/create-account")
	public UserResource createAccount(@RequestBody UserResource userResource) {
		return userService.createAccount(userResource);
	}
	
	/**
	 * Create account with re-captcha challenge
	 * @param userResource
	 * @return
	 */
	@PostMapping("/create-account-with-captcha")
	public UserResource createAccountWithCaptcha(@RequestBody UserResource userResource, 
			@RequestParam(name="recaptcha") String recaptchaResponse, HttpServletRequest request) {
		logger.debug("Recaptcha {}", recaptchaResponse);
		verifyCaptcha(recaptchaResponse, request);
		return userService.createAccount(userResource);
	}	
	
	private ResponseEntity<?> verifyCaptcha(String recaptchaResponse, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(captchaVerifyMessage)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.badRequest().body(Boolean.TRUE);
	}


	/***
	 * Verify the contact (i.e login Id)
	 * @param contactVerify
	 * @return
	 */
	@PostMapping("/verify")
	public boolean verify(@RequestBody ContactVerify contactVerify) {
		return userService.verifyContact(contactVerify);
	}
	
	/**
	 * @param payload (email)
	 * @return
	 */
	@PatchMapping(value = "/request-otp", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> requestOtpToVerifyAccount(@RequestParam String purpose, 
			@RequestParam(name="recaptcha") String recaptchaResponse, HttpServletRequest request,
			@RequestBody Map<String, Object> payload) {
		logger.debug("Requested OTP for the ");
		//Start of Verify reCaptcha
		// verifyCaptcha(recaptchaResponse, request);
		//End of reCaptcha
		return new ResponseEntity<>(userService.requestOtp(payload, purpose), HttpStatus.OK);
	}
	
	
	@PatchMapping(value = "/reset-password", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public boolean resetPassword(@RequestBody ResetPassword resetPassword) {
		logger.debug("Reset password ");
		return userService.resetPassword(resetPassword);
	}
	
	
	@PutMapping("/update")
	public UserResource updateName(@RequestBody UserResource userResource, HttpServletRequest request) {
		return userService.update(userResource);
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
	public UserResource createAccount(@PathVariable UUID loginUuid) {
		return userService.get(loginUuid);
	}
	
	@GetMapping("/user-by-role/{roleCd}")
	public List<UserResource> listUserByRole(@PathVariable Long roleCd, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "20") int size) {
		return userService.listUserByRole(roleCd, page, size);
	}
	 
	@GetMapping("/login-histories/{loginUuid}")
	public PaginatedLoginHistory loginHistories(@PathVariable UUID loginUuid,
			@RequestParam(required = false, defaultValue = "20") int page, 
			@RequestParam(required = false, defaultValue = "0") int size) {
		return userService.loginHistories(loginUuid, page, size);
	}
	
	@GetMapping("")
	public ModelAndView user(@RequestParam(defaultValue = "0", required = false) long page,
            @RequestParam(defaultValue = "30", required = false) long size,
            @RequestParam(defaultValue = "rowCreatedOn", required = false) String sortBy,
            @RequestParam(required = false) boolean asc, Model model) {		
		var paginatedUser = userService.list(new in.thirumal.model.Pagination(page, size, sortBy, asc));
		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.addObject("user", paginatedUser);
	    return modelAndView;
	}
		
}
