package com.jobplus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.ReportInfoConfigMapper;
import com.jobplus.dao.ReportInfoMapper;
import com.jobplus.pojo.GridQuery;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.ReportInfo;
import com.jobplus.pojo.ReportInfoConfig;
import com.jobplus.pojo.User;
import com.jobplus.service.IReportInfoService;
import com.jobplus.service.ISequenceService;
import com.jobplus.utils.GridDataUtil;

@Service("reportInfoService")
public class ReportInfoServiceImpl implements IReportInfoService{
	
	@Resource
	private ISequenceService seqService;
	@Resource
	private ReportInfoMapper reportInfoDao;
	@Resource
	private ReportInfoConfigMapper reportInfoConfigDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return reportInfoDao.deleteByPrimaryKey(id);
	}
	@Transactional
	@Override
	public int insert(ReportInfo record) {
		int id = seqService.getSeqByTableName("tbl_reportInfo");
		record.setId(id);
		try {
			//获取举报类型
			record.setReporttype(record.getREPORTTYPES()[record.getREPORTTYPE_INDEX()]);
		} catch (Exception e) {
			return 0;
		}		
		return reportInfoDao.insert(record);
	}
	@Override
	public int insertSelective(ReportInfo record) {
		return reportInfoDao.insert(record);
	}
	@Override
	public ReportInfo selectByPrimaryKey(Integer id) {
		return null;
	}
	@Override
	public int updateByPrimaryKeySelective(ReportInfo record) {
		return 0;
	}
	@Override
	public int updateByPrimaryKeyWithBLOBs(ReportInfo record) {
		return 0;
	}
	@Override
	public List<ReportInfoConfig> getReportInfoConfigList() {
		
		return reportInfoConfigDao.getList();
	}
	/**
	 * 分页获取所有的举报信息
	 * @param record
	 * @return
	 */
	@Override
	public Map<String, Object> getAllReportInfo(GridQuery gridQuery) {
		ReportInfo record =  gridQuery.getCondition() == null ? new ReportInfo() : (ReportInfo) gridQuery.getCondition();
		List<ReportInfo> list = null;
		int count = reportInfoDao.getAllReportInfoCount(record);
		if (count > 0) {
			record.setPageNo(gridQuery.getPage());
			record.setPageSize(gridQuery.getRows());
			record.setLimitSt(record.getPageNo() * record.getPageSize() - record.getPageSize());
			list = reportInfoDao.getAllReportInfo(record);
		}
		return GridDataUtil.getGridMap(gridQuery.getRows(), gridQuery.getPage(), count, list);
	}
	/*public Page<ReportInfo> getAllReportInfo(ReportInfo record) {
		Page<ReportInfo> page = new Page<ReportInfo>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<ReportInfo> list = reportInfoDao.getAllReportInfo(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
		}
		return page;
	}*/
	
}
