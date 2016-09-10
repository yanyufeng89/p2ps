package com.jobplus.service;

import java.util.List;

import com.jobplus.pojo.ReportInfo;
import com.jobplus.pojo.ReportInfoConfig;

public interface IReportInfoService {

	int deleteByPrimaryKey(Integer id);

	int insert(ReportInfo record);

	int insertSelective(ReportInfo record);

	ReportInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ReportInfo record);

	int updateByPrimaryKeyWithBLOBs(ReportInfo record);

	//暂时注释  此方法为全量更新
//	int updateByPrimaryKey(ReportInfo record);

	public List<ReportInfoConfig> getReportInfoConfigList();
	
}
