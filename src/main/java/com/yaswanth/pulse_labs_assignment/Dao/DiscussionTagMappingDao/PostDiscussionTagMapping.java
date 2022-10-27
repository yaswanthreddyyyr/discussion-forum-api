package com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostDiscussionTagMapping {
    @Autowired
    JdbcTemplate jdbc;

    public int insert(Long discussionId,Long tagId){
        String query="insert into discussiontagmapping values (?,?)";
        int result=0;
        try{
            result=jdbc.update(query,discussionId,tagId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  result;
    }
}
