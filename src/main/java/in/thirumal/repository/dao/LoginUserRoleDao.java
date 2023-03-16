/**
 * 
 */
package in.thirumal.repository.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.thirumal.exception.BadRequestException;
import in.thirumal.exception.ResourceNotFoundException;
import in.thirumal.model.LoginUserRole;
import in.thirumal.repository.LoginUserRoleRepository;

/**
 * @author Thirumal
 *
 */
@Repository
public class LoginUserRoleDao extends GenericDao implements LoginUserRoleRepository {

	private static final String PK                    = "login_user_role_id";
	
	private static final String CREATE                = "LoginUserRole.create";
	private static final String GET                   = "LoginUserRole.get";
	private static final String LIST                  = "LoginUserRole.list";
	private static final String LISTBY_LOGIN_USER_ID  = LIST + "ByLoginUserId";
	
	
	@Override
	public Long save(LoginUserRole loginUserRole) {
		KeyHolder holder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> setPreparedStatement(loginUserRole, con.prepareStatement(getSql(CREATE),
                    new String[] { PK })), holder);
            return Optional.ofNullable(holder.getKey())
                    .orElseThrow(()->new ResourceNotFoundException(primaryKeyErr)).longValue();
        } catch (DataIntegrityViolationException e) {
           logger.error("Login user role insert exception: {}", e.getMessage());
           throw new BadRequestException("Password is not added, Contact admin");
        }       
	}
	
	private PreparedStatement setPreparedStatement(LoginUserRole loginUserRole, PreparedStatement ps) throws SQLException {
		if(loginUserRole.getLoginUserId() == null) {
            ps.setObject(1, null);
        } else {
            ps.setLong(1, loginUserRole.getLoginUserId());
        }
		if(loginUserRole.getRoleCd() == null) {
            ps.setObject(2, null);
        } else {
            ps.setLong(2, loginUserRole.getRoleCd());
        }
		if(loginUserRole.getRemarks() == null) {
            ps.setObject(3, null);
        } else {
            ps.setString(3, loginUserRole.getRemarks());
        }
		return ps;
	}


	@Override
	public LoginUserRole findById(Long id) {
		logger.debug("Finding Login user role by Id {}", id);
		return jdbcTemplate.queryForObject(getSql(GET), loginUserRoleRowMapper, id);
	}

	@Override
	public List<LoginUserRole> findAllByLoginUserId(Long loginUserId) {
		logger.debug("Finding all login user role by login user id {}", loginUserId);
		return jdbcTemplate.query(getSql(LISTBY_LOGIN_USER_ID), loginUserRoleRowMapper, loginUserId);
	}
	
	@Override
	public int revoke(Long loginUserId) {
		return jdbcTemplate.update(getSql("LoginUserRole.revoke"), loginUserId);
	}
	
	RowMapper<LoginUserRole> loginUserRoleRowMapper = (rs, rowNum) -> {

		LoginUserRole loginUserRole = new LoginUserRole();

		loginUserRole.setLoginUserRoleId(rs.getObject(PK) != null ? rs.getLong(PK) : null);

		loginUserRole.setLoginUserId(rs.getObject("login_user_id") != null ? rs.getLong("login_user_id") : null);
		
		loginUserRole.setRoleCd(rs.getObject("role_cd") != null ? rs.getLong("role_cd") : null);
		
		loginUserRole.setRole(rs.getObject("role") != null ? rs.getString("role") : null);
		
		loginUserRole.setStartTime(rs.getObject("start_time") != null ? rs.getObject("start_time", OffsetDateTime.class) : null);
		
		loginUserRole.setStartTime(rs.getObject("end_time") != null ? rs.getObject("end_time", OffsetDateTime.class) : null);
		
		loginUserRole.setRemarks(rs.getObject("remarks") != null ? rs.getString("remarks") : null);
		
		return loginUserRole;
	};

}
