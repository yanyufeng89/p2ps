package com.jobplus.service;

import com.jobplus.pojo.SkillLibrary;

public interface ISkillLibraryService {
	int deleteByPrimaryKey(Integer id);

	int insert(SkillLibrary record);

	int insertSelective(SkillLibrary record);

	SkillLibrary selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SkillLibrary record);

	int updateByPrimaryKey(SkillLibrary record);
	
	/**
	 * 增加技能使用次数
	 * skillIds eg:1,2,3,4
	 * @param skillIds eg:1,2,3,4
	 * @return
	 */
	int updSkillUsesum(String skillIds);
}
