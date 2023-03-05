package in.thirumal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
public class UserResource implements Serializable {

	private static final long serialVersionUID = -7020619477594468968L;

	private UUID loginUuid;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private OffsetDateTime dateOfBirth;
	private OffsetDateTime accountCreatedOn;
	//
	private boolean forcePasswordChange;
	// Registered Client details
	private String registeredClientId;
	private Set<SimpleGrantedAuthority> authorities;
	
}
