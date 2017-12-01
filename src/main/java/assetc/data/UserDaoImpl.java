package assetc.data;

import assetc.model.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import assetc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findById(Integer userid) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);

		String sql = "SELECT * FROM users WHERE userid=:userid";

		User result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}


		return result;

	}

	@Override
	public List<User> findAll() {

		//String sql = "SELECT * FROM users";
                String sql = "SELECT * FROM users";
		List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());

		return result;

	}

	@Override
	public void save(User user) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO USERS(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ADDRESS, PHONE, ENABLED) "
				+ "VALUES ( :username, :password, :firstname, :lastname, :email, :address, :phone, :enabled)";

		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
                user.setUserid(keyHolder.getKey().intValue());
		
	}

	@Override
	public void update(User user) {

		String sql = "UPDATE USERS SET USERNAME=:username, PASSWORD=:password, FIRSTNAME=:firstname, LASTNAME=:lastname, EMAIL=:email, ADDRESS=:address, PHONE=:phone, ENABLED=:enabled WHERE userid=:userid";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	@Override
	public void delete(Integer userid) {
		String sql = "DELETE FROM USERS WHERE userid= :userid";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("userid", userid));
	}
        
        
        
        
        @Override
        public User validateUser(Login login) {
            String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword() + "'" ;
            List<User> users = namedParameterJdbcTemplate.query(sql, new UserMapper());
            return users.size() > 0 ? users.get(0) : null;  
        }
        
        @Override
        public int UserExists(String username) {
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("username", username);
        
            String sql = "SELECT count(*) FROM USERS WHERE username = :username";
            int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
            return count;

    }

        

	private SqlParameterSource getSqlParameterByModel(User user) {
            
		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("userid", user.getUserid());
		paramSource.addValue("username", user.getUsername());
                paramSource.addValue("password", user.getPassword());
                paramSource.addValue("firstname", user.getFirstname());
                paramSource.addValue("lastname", user.getLastname());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
                paramSource.addValue("phone", user.getPhone());
		paramSource.addValue("enabled", user.isEnabled());

		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
        
                        user.setUserid(rs.getInt("userid"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setFirstname(rs.getString("firstname"));
                        user.setLastname(rs.getString("lastname"));
                        user.setEmail(rs.getString("email"));
                        user.setAddress(rs.getString("address"));
                        user.setPhone(rs.getLong("phone"));
                        user.setEnabled(rs.getBoolean("enabled"));
                        return user;
        
		}
	}

	
}