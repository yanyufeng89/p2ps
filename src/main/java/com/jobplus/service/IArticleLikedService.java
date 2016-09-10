package com.jobplus.service;

import com.jobplus.pojo.ArticleLiked;
import com.jobplus.pojo.User;

public interface IArticleLikedService {
	
	int deleteByPrimaryKey(ArticleLiked key);

	int insert(ArticleLiked record,String contextPath,User user);

	int insertSelective(ArticleLiked record);
}
