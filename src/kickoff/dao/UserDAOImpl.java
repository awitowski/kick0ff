package kickoff.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import kickoff.model.User;
import kickoff.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {

	private static final String CREATE_USER_QUERY = "INSERT INTO user(username, email, is_active, password) VALUES(:username, :email, :active, :password);";
	private static final String USER_ROLE_QUERY = "INSERT INTO user_role(username) VALUES (:username)";
	private static final String READ_USER_BY_ID = "SELECT * FROM user WHERE user_id = :id;";
	private static final String READ_USER_BY_USERNAME = "SELECT * FROM user WHERE username = :username;";
	private static final String UPDATE_USER = "UPDATE user SET email = :email, password = :password WHERE user_id = :user_id;";
	private static final String DEACTIVATE_OR_ACTIVATE_USER = "UPDATE user SET is_active = :active WHERE user_id = :user_id";

	private NamedParameterJdbcTemplate template;

	public UserDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}

	@Override
	public User create(User user) {
		User resultUser = new User(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		int update = template.update(CREATE_USER_QUERY, sqlParameterSource, keyHolder);
		if (update > 0) {
			resultUser.setId((long) keyHolder.getKey());
			setRole(resultUser);
		}
		return resultUser;
	}

	private void setRole(User user) {

		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
		template.update(USER_ROLE_QUERY, sqlParameterSource);
	}

	@Override
	public User read(Long primaryKey) {
		User resultUser = null;
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", primaryKey);
		List<User> userList = template.query(READ_USER_BY_ID, sqlParameterSource, new UserRowMapper());
		if (userList.get(0) != null) {
			resultUser = userList.get(0);
		}
		return resultUser;
	}

	@Override
	public boolean update(User user) {
		boolean result = false;
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("email", user.getEmail());
		parameterMap.put("password", user.getPassword());
		parameterMap.put("user_id", user.getId());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
		int update = template.update(UPDATE_USER, sqlParameterSource);
		if (update > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivateUser(Long userId) {
		boolean result = false;
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("active", false);
		parameterMap.put("user_id", userId);
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
		int deactivate = template.update(DEACTIVATE_OR_ACTIVATE_USER, sqlParameterSource);
		if (deactivate > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean activateUser(Long userId) {
		boolean result = false;
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("active", true);
		parameterMap.put("user_id", userId);
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
		int deactivate = template.update(DEACTIVATE_OR_ACTIVATE_USER, sqlParameterSource);
		if (deactivate > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		User resultUser = null;
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("username", username);
		resultUser = template.queryForObject(READ_USER_BY_USERNAME, sqlParameterSource, new UserRowMapper());
		return resultUser;
	}

	private class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setEmail(resultSet.getString("email"));
			user.setActive(resultSet.getBoolean(4));

			return user;
		}

	}

	

}
