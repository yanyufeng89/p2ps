package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.EducationBgrdMapper;
import com.jobplus.pojo.EducationBgrd;
import com.jobplus.service.IEducationBgrdService;
import com.jobplus.service.ISequenceService;

@Service("educationBgrdService")
public class EducationBgrdServiceImpl implements IEducationBgrdService{

	@Resource
	private EducationBgrdMapper educationBgrdDao;
	@Resource
	private ISequenceService seqService;
	
	
	
	///* 新增或者更新教育背景     返回id值
	@Override
	public int insertOrUpdate(EducationBgrd record) {
		int ret = 0;
		if(record.getId()!=null){
			//更新工作经历
			ret = educationBgrdDao.updateByPrimaryKeySelective(record);
		}else{
			//新增工作经历
			int id = seqService.getSeqByTableName("tbl_educationBgrd");
			record.setId(id);
			ret = educationBgrdDao.insert(record);
		}		
		if(ret > 0){
			//返回id值
			ret = record.getId();
		}
		return ret;
	}
	//* 获取教育背景  list
	@Override
	public List<EducationBgrd> getExperList(EducationBgrd record) {
		
		return educationBgrdDao.getList(record);
	}
	@Override
	public int updateByPrimaryKeySelective(EducationBgrd record) {
		
		return educationBgrdDao.updateByPrimaryKey(record);
	}

}
