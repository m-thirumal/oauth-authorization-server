/**
 * 
 */
package in.thirumal.model;

/**
 * @author Thirumal
 *
 */
public record Pagination(long page, long size, String sortBy, boolean asc) {

	
	public long getOffset() {
		return page * size;
	}
}
