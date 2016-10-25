package com.jobplus.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SupportListMapper;
import com.jobplus.pojo.SupportList;
import com.jobplus.service.ISupportListService;

@Service("supportListService")
public class SupportListServiceImpl implements ISupportListService{
	
	@Resource
	private SupportListMapper suptDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return suptDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SupportList record) {
		
		return suptDao.insert(record);
	}

	@Override
	public int insertSelective(SupportList record) {
		
		return suptDao.insertSelective(record);
	}

	@Override
	public SupportList selectByPrimaryKey(Integer id) {
		
		return suptDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SupportList record) {
		
		return suptDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SupportList record) {
		
		return suptDao.updateByPrimaryKey(record);
	}

}
