package com.jobplus.dao;

import com.jobplus.pojo.PersonalSkill;

public interface PersonalSkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonalSkill record);

    int insertSelective(PersonalSkill record);

    PersonalSkill selectByPrimaryKey(Integer id);
    
    PersonalSkill selectByRecord(PersonalSkill record);

    int updateByPrimaryKeySelective(PersonalSkill record);

    int updateByPrimaryKeyWithBLOBs(PersonalSkill record);

    int updateByPrimaryKey(PersonalSkill record);
}