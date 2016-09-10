package com.jobplus.dao;

import com.jobplus.pojo.TopicsLiked;

public interface TopicsLikedMapper {
    int deleteByPrimaryKey(TopicsLiked key);

    int insert(TopicsLiked record);

    int insertSelective(TopicsLiked record);
}