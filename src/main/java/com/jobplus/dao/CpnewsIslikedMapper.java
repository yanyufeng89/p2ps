package com.jobplus.dao;

import com.jobplus.pojo.CpnewsIslikedKey;

public interface CpnewsIslikedMapper {
    int deleteByPrimaryKey(CpnewsIslikedKey key);

    int insert(CpnewsIslikedKey record);

    int insertSelective(CpnewsIslikedKey record);
}