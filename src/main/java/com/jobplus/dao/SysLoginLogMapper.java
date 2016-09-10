package com.jobplus.dao;

import com.jobplus.pojo.SysLoginLog;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);
}