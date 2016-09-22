package com.jobplus.service;

import com.jobplus.pojo.PersonalSkill;

public interface IPersonalSkillService {

	int deleteByPrimaryKey(Integer id);

	int insert(PersonalSkill record);

	int insertSelective(PersonalSkill record);

	PersonalSkill selectByPrimaryKey(Integer id);
	
	PersonalSkill selectByRecord(PersonalSkill record);

	int updateByPrimaryKeySelective(PersonalSkill record);

	int updateByPrimaryKeyWithBLOBs(PersonalSkill record);

	int updateByPrimaryKey(PersonalSkill record);
	
	/**
	 * 新增或者修改个人技能
	 */
	int insertOrUpdSkill(PersonalSkill record,String skillIds);
	
	
}
