package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.ReportInfoConfigMapper;
import com.jobplus.dao.ReportInfoMapper;
import com.jobplus.pojo.ReportInfo;
import com.jobplus.pojo.ReportInfoConfig;
import com.jobplus.service.IReportInfoService;
import com.jobplus.service.ISequenceService;

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
			record.setReporttype(record.getREPORTTYPE()[record.getREPORTTYPE_INDEX()]);
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
	
	
}
