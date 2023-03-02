/**
 * 
 */
package in.thirumal.client;

import org.springframework.stereotype.Component;

import in.thirumal.model.Email;
import in.thirumal.model.Message;

/**
 * @author Thirumal
 *
 */
@Component
public class MessageServiceFallback implements MessageServiceClient {

	@Override
	public Message send(Message message) {
		return null;
	}

	@Override
	public Email send(Email email) {
		return null;
	}

}
