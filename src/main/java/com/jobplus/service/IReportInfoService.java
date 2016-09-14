package com.jobplus.service;

import java.util.List;
import java.util.Map;

import com.jobplus.pojo.GridQuery;
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
	
	/**
	 * 分页获取所有的举报信息
	 * @param record
	 * @return
	 */
	public Map<String, Object> getAllReportInfo(GridQuery gridQuery);
//	public Page<ReportInfo> getAllReportInfo(ReportInfo record);
}
