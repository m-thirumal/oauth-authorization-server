/**
 * 
 */
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
 * @author Thirumal
 *
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder@ToString
public class LoginUserName implements Serializable {

	private static final long serialVersionUID = -8753030299971899838L;

	private Long loginUserNameId;
	private Long loginUserId;
	private String firstName;
	private String middleName;
	private String lastName;
	private OffsetDateTime rowCreatedOn;
	
}
