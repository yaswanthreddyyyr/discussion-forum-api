package com.yaswanth.pulse_labs_assignment.Dao.TagsDao;

import com.yaswanth.pulse_labs_assignment.Entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class GetTagsDao {
    @Autowired
    JdbcTemplate jdbc;
    public List<Tag> getTags(long discussionId)  throws SQLException {
        List<Tag> tags= Collections.emptyList();
        String query ="select * from tag  join discussiontagmapping on discussiontagmapping.tagId = tag.tagId and discussionId = ? ";
        try{
            System.out.println("Entered Dao for Tags");
            tags = jdbc.query(query,new BeanPropertyRowMapper<>(Tag.class),discussionId);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return tags;
    }
    public Long getTagId(String tagName){
        String query="select tagId from tag where tagName = ?";
        List<Tag> tagObjects =  Collections.emptyList();
        try{
            tagObjects = jdbc.query(query,new BeanPropertyRowMapper<>(Tag.class),tagName);

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(tagObjects.size()==0){
            return -1L;
        }
        return  tagObjects.get(0).getTagId();
    }
}
