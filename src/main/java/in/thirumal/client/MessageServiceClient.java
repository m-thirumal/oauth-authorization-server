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
	
	@PostMapping(value = "/sms/2")
	Message send(@RequestBody Message message);

	
	@PostMapping(value = "/email/2")
	Email send(@RequestBody Email email);
	
}