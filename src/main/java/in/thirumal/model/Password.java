/**
 * 
 */
package in.thirumal.model;

import java.io.Serializable;

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
@ToString@Builder
public class Password implements Serializable {

	private static final long serialVersionUID = 2897050433726245292L;
	
	private Long passwordId;
	private Long loginUserId;
	private String secretKey;	

}
