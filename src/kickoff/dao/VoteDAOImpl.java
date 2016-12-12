package kickoff.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import kickoff.model.Vote;
import kickoff.model.VoteType;
import kickoff.util.ConnectionProvider;

public class VoteDAOImpl implements VoteDAO {

	private static final String CREATE_VOTE = "INSERT INTO vote(discovery_id, user_id, date, type) "
            + "VALUES (:discovery_id, :user_id, :date, :type);";
    private static final String READ_VOTE = "SELECT vote_id, discovery_id, user_id, date, type "
            + "FROM vote WHERE vote_id = :vote_id;";
    private static final String READ_VOTE_BY_DISCOVERY_USER_IDS = "SELECT vote_id, discovery_id, user_id, date, type "
            + "FROM vote WHERE user_id = :user_id AND discovery_id = :discovery_id;";
    private static final String UPDATE_VOTE = "UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id;";
     
    private NamedParameterJdbcTemplate template;
     
    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
     
    @Override
    public Vote create(Vote vote) {
        Vote voteCopy = new Vote(vote);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("discovery_id", voteCopy.getDiscoveryId());
        parameterMap.put("user_id", voteCopy.getUserId());
        parameterMap.put("date", voteCopy.getDate());
        parameterMap.put("type", voteCopy.getVoteType().toString());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
        int update = template.update(CREATE_VOTE, sqlParameterSource, holder);
        if(update > 0) {
            voteCopy.setId((Long)holder.getKey());
        }
        return voteCopy;
    }
 
    @Override
    public Vote read(Long primaryKey) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("vote_id", primaryKey);
        Vote vote = template.queryForObject(READ_VOTE, sqlParameterSource, new VoteRowMapper());
        return vote;
    }
 
    @Override
    public boolean update(Vote vote) {
        boolean result = false;
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("date", vote.getDate());
        parameterMap.put("type", vote.getVoteType().toString());
        parameterMap.put("vote_id", vote.getId());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
        int update = template.update(UPDATE_VOTE, sqlParameterSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }
 
    @Override
    public boolean delete(Long key) {
        return false;
    }
 
    @Override
    public List<Vote> getAll() {
        return null;
    }
 
    @Override
    public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("user_id", userId);
        parameterMap.put("discovery_id", discoveryId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameterMap);
        Vote vote = null;
        try {
            vote = template.queryForObject(READ_VOTE_BY_DISCOVERY_USER_IDS, sqlParameterSource, new VoteRowMapper());
        } catch(EmptyResultDataAccessException e) {
      
        }
        return vote;
    }
     
    private class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setDiscoveryId(resultSet.getLong("discovery_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
         
    }
}
