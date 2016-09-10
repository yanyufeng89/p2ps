package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.EducationBgrd;

public interface EducationBgrdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EducationBgrd record);

    int insertSelective(EducationBgrd record);

    EducationBgrd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EducationBgrd record);

    int updateByPrimaryKey(EducationBgrd record);
    
    /**
     * 获取教育背景list
     * @param record
     * @return
     */
    List<EducationBgrd> getList(EducationBgrd record);
}