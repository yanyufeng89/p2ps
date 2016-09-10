package com.jobplus.dao;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);
  //暂时注释  此方法为全量更新
    
    /**
     * 通过userid获取Account实体
     * @param userid
     * @return
     */
    Account getAccountByUserId(@Param(value="userID")int userid);
    
    
    
    int addPointsOrAddJiaMoney(Account record);
    
    /**
     * 更改用户账户金额
     * @param record
     * @return
     */
    int adOrDecAccount(Account record);
    
    
}