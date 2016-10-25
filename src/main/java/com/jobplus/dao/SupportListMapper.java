package com.jobplus.dao;

import com.jobplus.pojo.SupportList;

public interface SupportListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SupportList record);

    int insertSelective(SupportList record);

    SupportList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupportList record);

    int updateByPrimaryKey(SupportList record);
}