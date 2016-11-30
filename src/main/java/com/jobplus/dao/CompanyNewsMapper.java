package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.CompanyNews;

public interface CompanyNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyNews record);

    int insertSelective(CompanyNews record);

    CompanyNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyNews record);

    int updateByPrimaryKeyWithBLOBs(CompanyNews record);

    int updateByPrimaryKey(CompanyNews record);
    
    /**
     * 获取企业快讯
     */
    List<CompanyNews> getList(CompanyNews record);
    int getListCount(CompanyNews record);
}