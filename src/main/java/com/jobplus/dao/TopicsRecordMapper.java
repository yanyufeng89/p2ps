package com.jobplus.dao;

import com.jobplus.pojo.TopicsRecord;

public interface TopicsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicsRecord record);

    int insertSelective(TopicsRecord record);

    TopicsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicsRecord record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(TopicsRecord record);
}