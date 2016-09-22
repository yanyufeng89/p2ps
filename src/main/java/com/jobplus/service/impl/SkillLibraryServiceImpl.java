package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SkillLibraryMapper;
import com.jobplus.pojo.SkillLibrary;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISkillLibraryService;

@Service("skillLibraryService")
public class SkillLibraryServiceImpl implements ISkillLibraryService {

	@Resource
	private SkillLibraryMapper skillLibraryDao;
	@Resource
	private ISequenceService seqService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {

		return skillLibraryDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(SkillLibrary record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_skilllibrary");
		record.setId(id);
		record.setId(id);
		ret = skillLibraryDao.insert(record);
		return ret;
	}
	@Transactional
	@Override
	public int insertSelective(SkillLibrary record) {

		return skillLibraryDao.insertSelective(record);
	}

	@Override
	public SkillLibrary selectByPrimaryKey(Integer id) {

		return skillLibraryDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SkillLibrary record) {

		return skillLibraryDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SkillLibrary record) {

		return skillLibraryDao.updateByPrimaryKey(record);
	}
	
@Transactional
	@Override
	public int updSkillUsesum(String skillIds) {
		return skillLibraryDao.updSkillUsesum(skillIds);
	}

}
