package com.jobplus.dao;

import com.jobplus.pojo.SmsFilter;

public interface SmsFilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsFilter record);
    
    /**
     * @param record
     * @return
     */
    int insertOrUpd(SmsFilter record);

    int insertSelective(SmsFilter record);

    SmsFilter selectByPrimaryKey(Integer id);    

    int updateByPrimaryKeySelective(SmsFilter record);

    int updateByPrimaryKey(SmsFilter record);
    
    /**
     * 查询过滤等级参数      
     * #{userid,jdbcType=INTEGER} and filterItem = #{filteritem
     * @param record
     * @return
     */
    SmsFilter selFilterLvByRecord(SmsFilter record);
    /**
     * 获取过滤等级和粉丝ids
     */
    SmsFilter getFilterLvAndFansIds(SmsFilter record);
}