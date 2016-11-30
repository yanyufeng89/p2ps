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
	 * 新增快讯
	 */
	CompanyNews insertCpNews(CompanyNews record);
	
	/**
	 * 删除企业快讯
	 */
	int delCpNews(CompanyNews record);
}
