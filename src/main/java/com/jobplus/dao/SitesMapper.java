package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Sites;

public interface SitesMapper {

	/**
	 * @param record
	 * @return
	 */
	Sites selectByRecord(Sites record);

	/**
	 * 阅读数 ++
	 * 
	 * @param id
	 * @return
	 */
	int updateReadSum(Integer id);

	/**
	 * 我分享的站点列表
	 * 
	 * @param record
	 * @return
	 */
	public List<Sites> getSharedSiteList(Sites record);

	/**
	 * 我收藏的站点列表
	 * 
	 * @param record
	 * @return
	 */
	public List<Sites> getCollectedSiteList(Sites record);

	/**
	 * 批量删除我分享的站点
	 * 
	 * @param condition
	 * @return
	 */
	public int delSharedSites(@Param(value = "condition") String[] condition);

	int deleteByPrimaryKey(Integer id);

	int insert(Sites record);

	int insertSelective(Sites record);

	Sites selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Sites record);

	int updateByPrimaryKeyWithBLOBs(Sites record);

	int updateByPrimaryKey(Sites record);
}