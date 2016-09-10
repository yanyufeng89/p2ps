package com.jobplus.service;

import com.jobplus.pojo.CoursesLiked;
import com.jobplus.pojo.User;

public interface ICoursesLikedService {

	int deleteByPrimaryKey(CoursesLiked key);

	int insert(CoursesLiked record,String contextPath,User user);

	int insertSelective(CoursesLiked record);
}
