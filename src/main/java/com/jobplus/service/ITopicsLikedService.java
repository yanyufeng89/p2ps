package com.jobplus.service;

import com.jobplus.pojo.TopicsLiked;
import com.jobplus.pojo.User;

/**
 * 话题点赞记录表
 * 
 * @author Jerry
 * 2016年7月6日下午7:05:06
 *
 */
public interface ITopicsLikedService {
	int deleteByPrimaryKey(TopicsLiked key);

    int insert(TopicsLiked record,String contextPath,User user);

    int insertSelective(TopicsLiked record);
}
