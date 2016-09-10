package com.jobplus.dao;

import com.jobplus.pojo.BookLiked;

public interface BookLikedMapper {
    int deleteByPrimaryKey(BookLiked key);

    int insert(BookLiked record);

    int insertSelective(BookLiked record);
}