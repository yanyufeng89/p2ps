package com.jobplus.service;

import java.util.Map;

import com.jobplus.pojo.SmsFilter;

public interface ISmsFilterService {

//	int deleteByPrimaryKey(Integer id);
//
//    int insert(SmsFilter record);
//
//    int insertSelective(SmsFilter record);
//
//    SmsFilter selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(SmsFilter record);
//
//    int updateByPrimaryKey(SmsFilter record);
    
    /**
     * @param record
     * @return
     */
    int insertOrUpd(SmsFilter record);

    /**
     * 获取消息设置参数 列     * 
	 * Map:ntsSmsF   prvtSmsF
     * @param record
     * @return
     */
    Map<Object, Object> getSmsFilterParm(SmsFilter record);
    
    /**
     * 获取过滤等级和粉丝ids
     * @param record
     * @return
     */
    SmsFilter getFilterLvAndFansIds(SmsFilter record);
	
}
