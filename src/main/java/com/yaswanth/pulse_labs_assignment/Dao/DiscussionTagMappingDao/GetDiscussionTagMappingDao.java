package com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao;

import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.GetTagsDao;
import com.yaswanth.pulse_labs_assignment.Entities.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class GetDiscussionTagMappingDao {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    GetTagsDao tagsDao;

    public List<Discussion> getDiscussionIdsRelatedToTag(Long pageNumber,String tagName){
        Long offset = pageNumber * 10;
        Long tagId = tagsDao.getTagId(tagName);
        String query="select discussionId from discussiontagmapping where tagId = ? limit 10 offset ?";
        List<Discussion> discussions =  Collections.emptyList();
        List<Long> discussionIds = Collections.emptyList();
        try{
            discussions = jdbc.query(query,new BeanPropertyRowMapper<>(Discussion.class),tagId,offset);

        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        return  discussions;

    }
}
