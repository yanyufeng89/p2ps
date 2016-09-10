package com.jobplus.service;

import com.jobplus.pojo.SiteShare;
import com.jobplus.pojo.User;

import com.jobplus.pojo.Page;

public interface ISiteShareService {

	int deleteByPrimaryKey(Integer id);

	int insert(SiteShare record);

	int insertSelective(SiteShare record);

	SiteShare selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SiteShare record);

	int updateByPrimaryKey(SiteShare record);

	SiteShare insertAndReturnRecord(SiteShare record,String contextPath,User user);

	/**
	 * 获取评论列表
	 * 
	 * @param record
	 * @return
	 */
	Page<SiteShare> getList(SiteShare record);

	/**
	 * @param record
	 * @return
	 */
	int delComment(SiteShare record);
}
