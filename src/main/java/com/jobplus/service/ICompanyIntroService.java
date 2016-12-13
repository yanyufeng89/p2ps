package com.jobplus.service;

import com.jobplus.pojo.CompanyIntro;

public interface ICompanyIntroService {

	int deleteByPrimaryKey(Integer id);

	int insert(CompanyIntro record);

	int insertSelective(CompanyIntro record);

	CompanyIntro selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CompanyIntro record,Integer isEstTime);

	int updateByPrimaryKeyWithBLOBs(CompanyIntro record);

	int updateByPrimaryKey(CompanyIntro record);

	/**
	 * 更改企业图片
	 */
	int updImgurl(CompanyIntro record);
	
}
