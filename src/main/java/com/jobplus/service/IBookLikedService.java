package com.jobplus.service;

import com.jobplus.pojo.BookLiked;
import com.jobplus.pojo.User;

public interface IBookLikedService {
	int deleteByPrimaryKey(BookLiked key);

    int insert(BookLiked record,String contextPath,User user);

    int insertSelective(BookLiked record);
}
