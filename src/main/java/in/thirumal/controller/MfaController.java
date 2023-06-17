/**
 * 
 */
package in.thirumal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.thirumal.model.Mfa;
import in.thirumal.service.MfaService;

/**
 * @author Thirumal
 *
 */
@RestController
@RequestMapping("/mfa")
public class MfaController {
	
	Logger logger = LoggerFactory.getLogger(MfaController.class);
	
	MfaService mfaService;
	
	@PostMapping("")
	public Mfa enableMfa(Mfa mfa) {
		return mfaService.enable(mfa);
	}
	
}
