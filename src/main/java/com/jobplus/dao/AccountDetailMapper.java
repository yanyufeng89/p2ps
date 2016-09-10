package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.AccountDetail;

public interface AccountDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountDetail record);

    int insertSelective(AccountDetail record);

    AccountDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountDetail record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(AccountDetail record);
    
    
    /**
     * 获取列表
     * @param record
     * @return
     */
    List<AccountDetail> getListByRecord(AccountDetail record);
    
}