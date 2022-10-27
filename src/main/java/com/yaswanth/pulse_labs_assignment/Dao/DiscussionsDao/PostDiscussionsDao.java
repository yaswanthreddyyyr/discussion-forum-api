package com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PostDiscussionsDao {

    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    GetDiscussionsDao getDiscussionsDao;
    public int insertDiscussion(String discussion) throws SQLException {
        String query = "insert into discussion(discussion,createdAt) values(?,?)";
        Long isDiscussionAlreadyPresent =  getDiscussionsDao.getDiscussionId(discussion);
        int result = 0;
        if(isDiscussionAlreadyPresent ==0){
            try{
                result = jdbc.update(query,discussion,System.currentTimeMillis());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                throw new SQLException("Error while adding Discussion");
            }
        }
        else{
            result =1;
        }

        return  result;
    }
}
