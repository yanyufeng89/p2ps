package com.jobplus.dao;

import com.jobplus.pojo.DocRecord;

public interface DocRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocRecord record);

    int insertSelective(DocRecord record);

    DocRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DocRecord record);
  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(DocRecord record);
}