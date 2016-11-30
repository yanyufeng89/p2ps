package com.jobplus.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.CompanyIntroMapper;
import com.jobplus.pojo.CompanyIntro;
import com.jobplus.service.ICompanyIntroService;

@Service("companyIntroService")
public class CompanyIntroServiceImpl implements ICompanyIntroService {

	@Resource
	private CompanyIntroMapper conpanyIntroDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return conpanyIntroDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CompanyIntro record) {
		record.setCreatetime(new Date());
		return conpanyIntroDao.insert(record);
	}

	@Override
	public int insertSelective(CompanyIntro record) {
		
		return conpanyIntroDao.insertSelective(record);
	}

	@Override
	public CompanyIntro selectByPrimaryKey(Integer id) {
		
		return conpanyIntroDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyIntro record) {
		
		return conpanyIntroDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CompanyIntro record) {
		
		return conpanyIntroDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(CompanyIntro record) {
		
		return conpanyIntroDao.updateByPrimaryKey(record);
	}

	@Override
	public CompanyIntro selectByUserid(Integer id) {
		
		return conpanyIntroDao.selectByUserid(id);
	}

}
