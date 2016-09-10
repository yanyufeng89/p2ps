package com.jobplus.dao;

import com.jobplus.pojo.OperationSum;

public interface OperationSumMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(OperationSum record);

    int insertSelective(OperationSum record);

    /**
     * 根据用户id获取用户操作数
     * @param userid
     * @return
     */
    OperationSum selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(OperationSum record);

    /**
     * 更新用户操作数
     * @param record
     * @return
     */
  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(OperationSum record);
    
    int updOperationSum(OperationSum record);
}