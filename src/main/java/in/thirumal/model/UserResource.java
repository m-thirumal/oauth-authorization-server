package in.thirumal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */

/**
 * @author Thirumal
 *
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder@ToString
public class UserResource implements Serializable {

	private static final long serialVersionUID = -7020619477594468968L;

	private String email;
	private String phoneNumber;
	private OffsetDateTime dateOfBirth;
	
}
