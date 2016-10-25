package com.jobplus.dao;

import com.jobplus.pojo.SchoolInfo;

public interface SchoolInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);
}