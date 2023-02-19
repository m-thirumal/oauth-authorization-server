/**
 * 
 */
package in.thirumal.repository;

import java.util.List;

import in.thirumal.model.GenericCd;

/**
 * @author Thirumal
 *
 */
public interface GenericCdRepository {

	List<GenericCd> list(String tableName, Long localeCd);
}
