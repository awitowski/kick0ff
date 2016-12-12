package kickoff.service;


import java.sql.Timestamp;
import java.util.Date;

import kickoff.dao.DAOFactory;
import kickoff.dao.VoteDAO;
import kickoff.model.Vote;
import kickoff.model.VoteType;

public class VoteService {

	public Vote addVote(long discoveryId, long userId, VoteType voteType) {
        Vote vote = new Vote();
        vote.setDiscoveryId(discoveryId);
        vote.setUserId(userId);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        vote = voteDao.create(vote);
        return vote;
    }
     
    public Vote updateVote(long discoveryId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote voteToUpdate = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
        if(voteToUpdate != null) {
            voteToUpdate.setVoteType(voteType);
            voteDao.update(voteToUpdate);
        }
        return voteToUpdate;
    }
     
    public Vote addOrUpdateVote(long discoveryId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
        Vote resultVote = null;
        if(vote == null) {
            resultVote = addVote(discoveryId, userId, voteType);
        } else {
            resultVote = updateVote(discoveryId, userId, voteType);
        }
        return resultVote;
    }
     
    public Vote getVoteByDiscoveryUserId(long discoveryId, long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdDiscoveryId(userId, discoveryId);
        return vote;
    }

}
