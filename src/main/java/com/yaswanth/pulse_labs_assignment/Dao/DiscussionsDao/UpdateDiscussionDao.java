package com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao;

import com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao.DeleteDiscussionTagMappingDao;
import com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao.PostDiscussionTagMapping;
import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.GetTagsDao;
import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.PostTagsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class UpdateDiscussionDao {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    PostTagsDao postTagsDao;
    @Autowired
    GetTagsDao getTagsDao;
    @Autowired
    PostDiscussionTagMapping postDiscussionTagMapping;
    @Autowired
    DeleteDiscussionTagMappingDao deleteDiscussionTagMappingDao;
    public int updateDiscussion(Long discussionId,String discussion) throws SQLException {
        String query = "update discussion set discussion = ? where discussionId = ?";
        int result=0;
        try{
            result = jdbc.update(query,discussion,discussionId);
        }
        catch (Exception e){
            throw new SQLException("Error while updating Discussion");
        }
        return result;
    }
    public int updateDiscussion(Long discussionId,String discussion,String[] tags) throws SQLException{
        String query = "update discussion set discussion = ? where discussionId = ?";
        int result=0;
        int isPreviousTagsDeleted = 0;
        int isNewTagsAdded = 0;
        try{
            result = jdbc.update(query,discussion,discussionId);
        }
        catch (Exception e){
            throw new SQLException("Error while updating Discussion");
        }

        try{
            isPreviousTagsDeleted = deleteDiscussionTagMappingDao.deleteTagsCorrespondingDiscussionId(discussionId);
        }
        catch (Exception e){
            throw new SQLException("Error while deleting previous Tags");
        }
        for (String tag:tags){
            postTagsDao.insertTag(tag);
            Long tagId = getTagsDao.getTagId(tag);
            try{
                isNewTagsAdded= postDiscussionTagMapping.insert(discussionId,tagId);
            }
            catch (Exception e){
                throw new SQLException("Error while Adding new Tags");
            }


        }
        if( result >0 && isNewTagsAdded > 0 && isPreviousTagsDeleted>0){
            return  1;
        }
        return 0;

    }

}
