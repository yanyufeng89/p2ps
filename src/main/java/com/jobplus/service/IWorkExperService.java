package com.jobplus.service;

import java.util.List;

import com.jobplus.pojo.WorkExper;

public interface IWorkExperService {

//	int deleteByPrimaryKey(Integer id);
//
//    int insert(WorkExper record);
//
//    int insertSelective(WorkExper record);
//
//    WorkExper selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(WorkExper record);
//
//    int updateByPrimaryKey(WorkExper record);
    
    

    /**
     * 新增或者更新工作经历      返回id值 前端需要
     */
    int insertOrUpdate(WorkExper record);
    
    /**
     * 获取工作经历  list
     * @param record
     * @return
     */
    List<WorkExper> getExperList(WorkExper record);
}
