/**
 * 
 */
package in.thirumal.model;

import java.util.List;

/**
 * @author Thirumal
 *
 */
public record PaginatedUser(List<UserResource> userResources, long count) {

}
