/**
 * 
 */
package in.thirumal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.thirumal.model.Mfa;

/**
 * @author Thirumal
 *
 */
@Service
public class MfaService {

	Logger logger = LoggerFactory.getLogger(MfaService.class);
	
	public Mfa enable(Mfa mfa) {
		logger.debug("Enabling MFA {}", mfa);
		return null;
	}

}
