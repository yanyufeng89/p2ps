package com.jobplus.dao;


import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.MobileSms;

public interface MobileSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileSms record);

    int insertEmail(MobileSms record);

    int insertSelective(MobileSms record);

    MobileSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileSms record);

    int updateByPrimaryKey(MobileSms record);
    
    int isSendValidateCode(@Param("mobile")String mobile,@Param("ip")String ip);
    
    int updateByValidateCode(MobileSms sms);
}