package com.jobplus.dao;

import com.jobplus.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Resource record);
}