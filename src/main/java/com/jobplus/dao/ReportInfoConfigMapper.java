package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.ReportInfoConfig;

public interface ReportInfoConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportInfoConfig record);

    int insertSelective(ReportInfoConfig record);

    ReportInfoConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportInfoConfig record);

    int updateByPrimaryKey(ReportInfoConfig record);
    
    List<ReportInfoConfig> getList();
}