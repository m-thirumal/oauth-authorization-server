/**
 * 
 */
package in.thirumal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thirumal
 *
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder@ToString
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 8998709262269738118L;
	
	private Long loginUserId;
	private UUID loginUuid;
	private OffsetDateTime dateOfBirth;
	private OffsetDateTime rowCreatedOn;

}
