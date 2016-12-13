package com.jobplus.service;

import com.jobplus.pojo.CompanyNews;
import com.jobplus.pojo.Page;

public interface ICompanyNewsService {

	/**
	 * 获取企业快讯 list
	 * @param record
	 * @return
	 */
	Page<CompanyNews> getNewsList(CompanyNews record);
	
	/**
	 * 获取企业快讯 条数 
	 */
	public int getListCount(CompanyNews record);
	/**
	 * 新增快讯
	 */
	CompanyNews insertCpNews(CompanyNews record);
	
	/**
	 * 删除企业快讯
	 */
	int delCpNews(CompanyNews record);
	/**
	 * 置顶企业快讯
	 */
	int topCpNews(CompanyNews record);
	/**
	 * 获取某条快讯
	 */
	CompanyNews getOneNews(Integer id);
	
}
