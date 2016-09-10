package com.jobplus.dao;

import com.jobplus.pojo.ReportInfo;

public interface ReportInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportInfo record);

    int insertSelective(ReportInfo record);

    ReportInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportInfo record);

    int updateByPrimaryKeyWithBLOBs(ReportInfo record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(ReportInfo record);
}