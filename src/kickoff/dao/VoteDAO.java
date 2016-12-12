package kickoff.dao;

import kickoff.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
	
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);

}
