package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.WorkExper;

public interface WorkExperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkExper record);

    int insertSelective(WorkExper record);

    WorkExper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkExper record);

    int updateByPrimaryKey(WorkExper record);
    
    /**
     * 工作经历新增或者是更新
     */
//    int insertOrUpdate(WorkExper record);
    /**
	 * 获取工作经历 list
	 */
    List<WorkExper> getList(WorkExper record);
    
}