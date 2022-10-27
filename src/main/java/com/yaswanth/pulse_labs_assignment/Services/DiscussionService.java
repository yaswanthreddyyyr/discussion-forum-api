package com.yaswanth.pulse_labs_assignment.Services;


import com.yaswanth.pulse_labs_assignment.Dao.DiscussionTagMappingDao.PostDiscussionTagMapping;
import com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao.DeleteDiscussionDao;
import com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao.GetDiscussionsDao;
import com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao.PostDiscussionsDao;
import com.yaswanth.pulse_labs_assignment.Dao.DiscussionsDao.UpdateDiscussionDao;
import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.GetTagsDao;
import com.yaswanth.pulse_labs_assignment.Dao.TagsDao.PostTagsDao;
import com.yaswanth.pulse_labs_assignment.Entities.Discussion;
import com.yaswanth.pulse_labs_assignment.Entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {
    @Autowired
    GetDiscussionsDao getDiscussionsDao;
    @Autowired
    PostDiscussionsDao postDiscussionsDao;
    @Autowired
    GetTagsDao getTagsDao;
    @Autowired
    PostTagsDao postTagsDao;
    @Autowired
    PostDiscussionTagMapping postDiscussionTagMapping;
    @Autowired
    DeleteDiscussionDao deleteDiscussionDao;
    @Autowired
    UpdateDiscussionDao updateDiscussionDao;

    public List<Discussion> getDiscussions(Long pageNumber, Optional<String> tagName,Optional<String> searchValue,Optional<Long> fromDate,Optional<Long> toDate) throws SQLException {
        if(tagName.isPresent() && searchValue.isPresent()){
            if(fromDate.isPresent()&&toDate.isPresent()){
                return getDiscussionsDao.getDiscussions(pageNumber,tagName.get(),searchValue.get(), fromDate.get(), toDate.get());
            }
            return getDiscussionsDao.getDiscussions(pageNumber,tagName.get(),searchValue.get());
        }
        else if(tagName.isPresent()){
            if(fromDate.isPresent() && toDate.isPresent()){
                return getDiscussionsDao.getDiscussions(pageNumber,tagName.get(),fromDate.get(),toDate.get());
            }
            return getDiscussionsDao.getDiscussions(pageNumber,tagName.get());
        }
        else if (searchValue.isPresent()){
            if(fromDate.isPresent() && toDate.isPresent()){
                return getDiscussionsDao.getDiscussionsFromSearchValue(pageNumber,searchValue.get(),fromDate.get(),toDate.get());
            }
            return  getDiscussionsDao.getDiscussionsFromSearchValue(pageNumber,searchValue.get());
        }
        System.out.println("Entered Service ...");
        if(fromDate.isPresent() && toDate.isPresent()){
            return getDiscussionsDao.getDiscussions(pageNumber,fromDate.get(),toDate.get());
        }
        return getDiscussionsDao.getDiscussions(pageNumber);
    }
    public int insertDiscussion(String discussion,String[] tags) throws SQLException{
        int isDiscussionAdded = postDiscussionsDao.insertDiscussion(discussion);
        Long discussionId = getDiscussionsDao.getDiscussionId(discussion);
        if(discussionId ==0){
            System.out.println("Discussion Not Found to Add Tags");
            return  0;
        }
        for (String tag : tags){
            int isTagAdded = postTagsDao.insertTag(tag);
            Long tagId = getTagsDao.getTagId(tag);
            if(tagId == 0){
                System.out.println("Tag Not Found to Add into Mapping");
                return  0;
            }
            int isMappingAdded = postDiscussionTagMapping.insert(discussionId,tagId);
        }
        
        return isDiscussionAdded;
    }

    public int deleteDiscussion(Long discussionId) throws SQLException{
        return deleteDiscussionDao.deleteDiscussion(discussionId);
    }

    public int updateDiscussion(Request request) throws SQLException{
        if(request.getTags() != null){
            String[] tags = request.getTags().split(",");
            return updateDiscussionDao.updateDiscussion(request.getDiscussionId(),request.getDiscussion(),tags);

        }
        return updateDiscussionDao.updateDiscussion(request.getDiscussionId(),request.getDiscussion());
    }
}
