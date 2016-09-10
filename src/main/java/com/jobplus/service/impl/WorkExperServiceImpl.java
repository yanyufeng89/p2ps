package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.WorkExperMapper;
import com.jobplus.pojo.WorkExper;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.IWorkExperService;

@Service("workExperService")
public class WorkExperServiceImpl implements IWorkExperService{

	@Resource
	private WorkExperMapper workExperDao;
	@Resource
	private ISequenceService seqService;

	
	
	/**
	 * 新增或者更新工作经历   返回id值 前端需要
	 */
	@Override
	public int insertOrUpdate(WorkExper record) {
		int ret = 0;
		if(record.getId()!=null){
			//更新工作经历
			ret = workExperDao.updateByPrimaryKeySelective(record);
		}else{
			//新增工作经历
			int id = seqService.getSeqByTableName("tbl_workExper");
			record.setId(id);
			ret = workExperDao.insert(record);
		}
		if(ret > 0){
			//返回id值
			ret = record.getId();
		}
		return ret;
	}
	/**
	 * 获取工作经历 list
	 */
	@Override
	public List<WorkExper> getExperList(WorkExper record) {
		
		return workExperDao.getList(record);
	}

}
