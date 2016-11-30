package com.jobplus.service;

import com.jobplus.pojo.CompanyIntro;

public interface ICompanyIntroService {

	int deleteByPrimaryKey(Integer id);

	int insert(CompanyIntro record);

	int insertSelective(CompanyIntro record);

	CompanyIntro selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CompanyIntro record);

	int updateByPrimaryKeyWithBLOBs(CompanyIntro record);

	int updateByPrimaryKey(CompanyIntro record);
	
	/**
	 * 根据用户id获取公司信息
	 */
	CompanyIntro selectByUserid(Integer id);

}
