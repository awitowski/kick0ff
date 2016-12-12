package kickoff.dao;

import java.util.List;

import kickoff.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery, Long> {
	
	List<Discovery> getAll();
	List<Discovery> getAll(String username);
}
