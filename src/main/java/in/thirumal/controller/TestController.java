/**
 * 
 */
package in.thirumal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thirumal
 *
 */
@RestController
public class TestController {

	@GetMapping("/hi")
	public String createAccount() {
		System.out.println("hello");
		return "hi";
	}
	 
}
