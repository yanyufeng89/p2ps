package com.jobplus.service;

import com.jobplus.pojo.Sites;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface ISitesService {

	/**
	 * //同时 插入一条站点分享记录
	 * @param record
	 * @return
	 */
	int insertSiteAndSiteShare(Sites record);
	
	/**
	 * 我分享的站点列表
	 * @param record
	 * @return
	 */
	Page<Sites> getSharedSiteList(Sites record);
	
	/**
	 * 我收藏的站点列表
	 * @param record
	 * @return
	 */
	Page<Sites> getCollectedSiteList(Sites record);
	
	/**
	 * 批量删除个人中心-我分享的站点
	 * @param conditions
	 * @return
	 */
	int delSharedSites(String conditions[]);
	
	/**
	 * 获取站点详情 浏览数++
	 * @param record
	 * @return
	 */
	Sites getSiteDetail(Sites record);
	
	MyCollect collectSite(MyCollect record);
	
	
	int deleteByPrimaryKey(Integer id);

	int insert(Sites record);

	int insertSelective(Sites record);

	Sites selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Sites record);

	int updateByPrimaryKeyWithBLOBs(Sites record);

	int updateByPrimaryKey(Sites record);
}
