package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.SiteShare;

public interface SiteShareMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SiteShare record);

	int insertSelective(SiteShare record);

	SiteShare selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SiteShare record);

	int updateByPrimaryKey(SiteShare record);

	/**
	 * 获取评论列表
	 * 
	 * @param record
	 * @return
	 */
	List<SiteShare> getList(SiteShare record);
}