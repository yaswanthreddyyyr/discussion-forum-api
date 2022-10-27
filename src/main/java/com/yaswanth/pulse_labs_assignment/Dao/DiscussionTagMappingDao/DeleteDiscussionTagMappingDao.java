package com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class DeleteDiscussionTagMappingDao {
    @Autowired
    JdbcTemplate jdbc;

    public int deleteTagsCorrespondingDiscussionId(Long discussionId) throws SQLException {
        int result=0;
        String query="delete from discussiontagmapping where discussionId = ? ";
        try{
            result = jdbc.update(query,discussionId);
        }
        catch (Exception e){
            throw new SQLException("Error while deleting tags corresponding discussionId " + discussionId.toString());
        }
        return result;
    }
}
