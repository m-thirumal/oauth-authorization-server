/**
 * 
 */
package in.thirumal.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Thirumal
 */
@Component
public class SucessLogoutListener implements AuthenticationSuccessHandler{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println(authentication);
		logger.debug("Err ");
	}

}
