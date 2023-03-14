package in.thirumal.repository.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.thirumal.exception.BadRequestException;
import in.thirumal.exception.ResourceNotFoundException;
import in.thirumal.model.LoginUser;
import in.thirumal.repository.LoginUserRepository;

/**
 * @author Thirumal
 *
 */
@Repository
public class LoginUserDao extends GenericDao implements LoginUserRepository {

	private static final String PK = "login_user_id";
	
	private static final String CREATE    = "LoginUser.create";
	private static final String GET      = "LoginUser.get";
	private static final String GETBY_UUID = GET + "ByUuid"; 
	private static final String UPDATE     = "LoginUser.update";
	
	@Override
	public Long save(LoginUser loginUser) {
		KeyHolder holder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> setPreparedStatement(loginUser, con.prepareStatement(getSql(CREATE),
                    new String[] { PK })), holder);
            return Optional.ofNullable(holder.getKey())
                    .orElseThrow(()->new ResourceNotFoundException(primaryKeyErr)).longValue();
        } catch (DataIntegrityViolationException e) {
           logger.error("Login user insert exception: {}", e.getMessage());
           throw new BadRequestException("Login user is not added, Contact admin");
        }       
	}

	private PreparedStatement setPreparedStatement(LoginUser loginUser, PreparedStatement ps) throws SQLException {
		ps.setObject(1, loginUser.getDateOfBirth());
		return ps;
	}

	@Override
	public LoginUser findById(Long id) {
		logger.debug("Finding login user by Id {}", id);
		return jdbcTemplate.queryForObject(getSql(GET), loginUserRowMapper, id);
	}

	@Override
	public LoginUser findByUuid(UUID uuid) {
		logger.debug("Finding login user by UUID {}", uuid);
		try {
			return jdbcTemplate.queryForObject(getSql(GETBY_UUID), loginUserRowMapper, uuid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public int update(LoginUser loginUser) {
		logger.debug("Updateing login user dob {}", loginUser.getLoginUserId());
		return  jdbcTemplate.update(getSql(UPDATE), loginUser.getDateOfBirth(), loginUser.getLoginUserId());
	}	
	
	RowMapper<LoginUser> loginUserRowMapper = (rs, rowNum) -> {

		LoginUser loginUser = new LoginUser();

		loginUser.setLoginUserId(rs.getObject(PK) != null ? rs.getLong(PK) : null);

		loginUser.setLoginUuid(rs.getObject("login_uuid") != null ? rs.getObject("login_uuid", UUID.class) : null);
		
		loginUser.setDateOfBirth(rs.getObject("date_of_birth") != null ? rs.getObject("date_of_birth", OffsetDateTime.class) : null);

		loginUser.setRowCreatedOn(rs.getObject("row_created_on") != null ? rs.getObject("row_created_on", OffsetDateTime.class) : null);
 
		return loginUser;
	};
	

}
