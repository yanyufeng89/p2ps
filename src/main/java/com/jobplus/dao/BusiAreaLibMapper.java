package com.jobplus.dao;

import com.jobplus.pojo.BusiAreaLib;

public interface BusiAreaLibMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusiAreaLib record);

    int insertSelective(BusiAreaLib record);

    BusiAreaLib selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusiAreaLib record);

    int updateByPrimaryKey(BusiAreaLib record);
}