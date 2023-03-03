/**
 * 
 */
package in.thirumal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.thirumal.model.Email;
import in.thirumal.model.Message;

/**
 * @author Thirumal
 *
 */
@FeignClient(name = "message-service", configuration = MessageServiceConfig.class, fallback =  MessageServiceFallback.class)
public interface MessageServiceClient {
	
	@PostMapping(value = "/sms/3")
	Message send(@RequestBody Message message);

	
	@PostMapping(value = "/email/3")
	Email send(@RequestBody Email email);
	
}