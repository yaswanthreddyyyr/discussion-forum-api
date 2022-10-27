package com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class DeleteDiscussionDao {
    @Autowired
    JdbcTemplate jdbc;
    public int deleteDiscussion(Long discussionId) throws SQLException{
        String query ="delete from discussion where discussionId = ?";
        int result=0;
        try{
           result = jdbc.update(query,discussionId);
        }
        catch(Exception e){
            throw  new SQLException("Unable to delete Discussion");
        }
        return result;
    }
}
