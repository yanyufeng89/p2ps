package com.jobplus.service;

import com.jobplus.pojo.SitesLiked;
import com.jobplus.pojo.User;

public interface ISitesLikedService {
	int deleteByPrimaryKey(SitesLiked key);

	int insert(SitesLiked record,String contextPath,User user);

	int insertSelective(SitesLiked record);
}
