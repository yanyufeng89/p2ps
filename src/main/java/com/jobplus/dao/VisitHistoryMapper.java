package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.VisitHistory;

public interface VisitHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitHistory record);
    
    /**
     * 
     * @param record
     * @return
     */
    int insertOrUpdate(VisitHistory record);

    int insertSelective(VisitHistory record);

    VisitHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitHistory record);

    int updateByPrimaryKey(VisitHistory record);
    
    /**
     * 获取最近访问的人员
     * @param record
     * @return
     */
    List<VisitHistory> getRecentVistors(VisitHistory record);
}