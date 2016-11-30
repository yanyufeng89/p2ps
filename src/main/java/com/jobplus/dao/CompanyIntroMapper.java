package com.jobplus.dao;

import com.jobplus.pojo.CompanyIntro;

public interface CompanyIntroMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyIntro record);

    int insertSelective(CompanyIntro record);

    CompanyIntro selectByPrimaryKey(Integer id);
    
    CompanyIntro selectByUserid(Integer userid);

    int updateByPrimaryKeySelective(CompanyIntro record);

    int updateByPrimaryKeyWithBLOBs(CompanyIntro record);

    int updateByPrimaryKey(CompanyIntro record);
}