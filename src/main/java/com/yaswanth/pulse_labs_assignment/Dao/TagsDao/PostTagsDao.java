package com.yaswanth.pulse_labs_assignment.Dao.TagsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class PostTagsDao {
    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    GetTagsDao getTagsDao;
    public int insertTag(String tagName) throws SQLException {
        String query="insert into tag(tagName) values (?)";
        Long tagId = getTagsDao.getTagId(tagName);
        int result=0;
        if(tagId ==-1){
            try {
                result = jdbc.update(query,tagName.trim());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                throw new SQLException("Error while Adding Tag");
            }
        }
        return  result;
    }
}
