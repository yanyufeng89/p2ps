package com.jobplus.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jobplus.pojo.Sequence;
@Transactional(propagation=Propagation.NOT_SUPPORTED) 
public interface SequenceMapper {
    int deleteByPrimaryKey(String name);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
    
    int nextVal(String name);
}