package kickoff.service;

import kickoff.dao.DAOFactory;
import kickoff.model.User;

public class UserService {

	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		factory.getUserDAO().create(user);
	}

	public User getUserById(long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		User user = factory.getUserDAO().read(userId);
		return user;
	}

	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		User user = factory.getUserDAO().getUserByUsername(username);
		return user;
	}

	public boolean updateUser(User user) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		boolean result = factory.getUserDAO().update(user);
		return result;
	}

	public boolean deactivateUser(Long userId){
		DAOFactory factory = DAOFactory.getDAOFactory();
		boolean result = factory.getUserDAO().deactivateUser(userId);
		return result;
	}
	
	public boolean activateUser(Long userId){
		DAOFactory factory = DAOFactory.getDAOFactory();
		boolean result = factory.getUserDAO().activateUser(userId);
		return result;
	}

}
