package com.jobplus.dao;

import com.jobplus.pojo.ArticleLiked;

public interface ArticleLikedMapper {
    int deleteByPrimaryKey(ArticleLiked key);

    int insert(ArticleLiked record);

    int insertSelective(ArticleLiked record);
}