package com.jobplus.dao;

import com.jobplus.pojo.IndexGenerate;
import com.jobplus.pojo.IndexGenerateWithBLOBs;

public interface IndexGenerateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexGenerateWithBLOBs record);

    int insertSelective(IndexGenerateWithBLOBs record);

    IndexGenerateWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IndexGenerateWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(IndexGenerateWithBLOBs record);

    int updateByPrimaryKey(IndexGenerate record);
}