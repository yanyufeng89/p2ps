package com.jobplus.dao;

import com.jobplus.pojo.CoursesLiked;

public interface CoursesLikedMapper {
    int deleteByPrimaryKey(CoursesLiked key);

    int insert(CoursesLiked record);

    int insertSelective(CoursesLiked record);
}