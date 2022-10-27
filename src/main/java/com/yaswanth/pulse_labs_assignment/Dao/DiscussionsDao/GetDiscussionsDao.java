package com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao;


import com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao.GetDiscussionTagMappingDao;
import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.GetTagsDao;
import com.yaswanth.pulse_labs_assignment.Entities.Discussion;
import com.yaswanth.pulse_labs_assignment.Entities.Tag;
import com.yaswanth.pulse_labs_assignment.Services.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class GetDiscussionsDao {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    GetTagsDao tagsDao;
    @Autowired
    HelperService helperService;
    @Autowired
    GetDiscussionTagMappingDao discussionTagMappingDao;
    public List<Discussion> getDiscussions(Long pageNumber)  throws SQLException{
        Long offset=pageNumber * 10;
        String query="select * from discussion limit 10 offset ? ";
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),offset);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussions(Long pageNumber,Long fromDate, Long toDate)  throws SQLException{
        Long offset=pageNumber * 10;
        String query="select * from discussion where createdAt between ? and ? limit 10 offset ? ";
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),fromDate,toDate,offset);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussions(Long pageNumber,String tagName)  throws SQLException{
        Long offset=pageNumber * 10;
        String discussionIds = helperService.convertListToString(discussionTagMappingDao.getDiscussionIdsRelatedToTag(pageNumber,tagName));
        System.out.println(discussionIds);
        String query="select * from discussion where discussionId in " + discussionIds;
        System.out.println(query);
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions with TagName Filter");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class));
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussions(Long pageNumber,String tagName,Long fromDate,Long toDate)  throws SQLException{
        Long offset=pageNumber * 10;
        String discussionIds = helperService.convertListToString(discussionTagMappingDao.getDiscussionIdsRelatedToTag(pageNumber,tagName));
        System.out.println(discussionIds);
        String query="select * from discussion where discussionId in " + discussionIds +"and createdAt between ? and ?";
        System.out.println(query);
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions with TagName Filter");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),fromDate,toDate);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }

    public List<Discussion> getDiscussions(Long pageNumber,String tagName,String searchValue)  throws SQLException{
        Long offset=pageNumber * 10;
        Long tagId = tagsDao.getTagId(tagName);
        String query="select * from discussion join discussiontagmapping on discussion.discussionId = discussiontagmapping.discussionId and discussiontagmapping.tagId = ? and discussion like ? limit 10 offset ?";
        System.out.println(query);
        List<Discussion> discussions= Collections.emptyList();
        try{

            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),tagId,'%'+searchValue+'%',offset);
            System.out.println(discussions);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussions(Long pageNumber,String tagName,String searchValue,Long fromDate,Long toDate)  throws SQLException{
        Long offset=pageNumber * 10;
        Long tagId = tagsDao.getTagId(tagName);
        String query="select * from discussion join discussiontagmapping on discussion.discussionId = discussiontagmapping.discussionId and discussiontagmapping.tagId = ? and discussion like ? and createdAt between ? and ? limit 10 offset ?";
        System.out.println(query);
        List<Discussion> discussions= Collections.emptyList();
        try{

            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),tagId,'%'+searchValue+'%',fromDate,toDate,offset);
            System.out.println(discussions);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussionsFromSearchValue(Long pageNumber,String searchValue)  throws SQLException{
        Long offset=pageNumber * 10;
        String query="select * from discussion  where discussion.discussion like ? limit 10 offset ?";
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),'%'+searchValue+'%',offset);
            System.out.println(discussions);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }
    public List<Discussion> getDiscussionsFromSearchValue(Long pageNumber,String searchValue,Long fromDate,Long toDate)  throws SQLException{
        Long offset=pageNumber * 10;
        String query="select * from discussion  where discussion.discussion like ? and createdAt between ? and ? limit 10 offset ?";
        List<Discussion> discussions= Collections.emptyList();
        try{
            System.out.println("Entered Dao for discussions");
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),'%'+searchValue+'%',fromDate,toDate,offset);
            System.out.println(discussions);
            for(int i=0;i<discussions.size();i++){
                List<Tag> tags= Collections.emptyList();
                try{
                    System.out.println("Entered Dao for Tags");
                    tags = tagsDao.getTags(discussions.get(i).getDiscussionId());
                    discussions.get(i).setTags(tags);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }

            }
            return discussions;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return discussions;
    }

    public Long getDiscussionId (String discussion){
        String query ="select * from discussion where discussion = ?";
        List<Discussion> discussions= Collections.emptyList();
        try{
            discussions = jdbc.query(query, new BeanPropertyRowMapper<>(Discussion.class),discussion);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(discussions.size()==0){
            return 0L;
        }
        return discussions.get(0).getDiscussionId();

    }

}
