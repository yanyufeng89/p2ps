package com.jobplus.service;

import java.util.List;

import com.jobplus.pojo.EducationBgrd;

public interface IEducationBgrdService {

	

    int updateByPrimaryKeySelective(EducationBgrd record);
	/**
     * 新增或者更新教育背景    返回id值
     */
    int insertOrUpdate(EducationBgrd record);
    /**
     * 获取教育背景  list
     * @param record
     * @return
     */
    List<EducationBgrd> getExperList(EducationBgrd record);
    

    int deleteByPrimaryKey(Integer id);
}
