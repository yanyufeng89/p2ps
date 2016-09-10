package com.jobplus.dao;

import com.jobplus.pojo.MyFolder;

public interface MyFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyFolder record);

    int insertSelective(MyFolder record);

    MyFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyFolder record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(MyFolder record);
}