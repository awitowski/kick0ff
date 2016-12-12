package kickoff.dao;

import java.util.List;

import kickoff.model.User;

public interface UserDAO extends GenericDAO<User, Long>{
	
	List<User> getAll();
	User getUserByUsername(String username);
	boolean deactivateUser(Long userId);
	boolean activateUser(Long userId);
}
