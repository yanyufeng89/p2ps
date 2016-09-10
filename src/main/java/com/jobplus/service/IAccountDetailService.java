package com.jobplus.service;

import com.jobplus.pojo.AccountDetail;
import com.jobplus.pojo.Page;

public interface IAccountDetailService {
	
	public int insertAccountDetail(AccountDetail accountDetail);
	
	/**
	 * 获取账户详情列表    收入或者支出    page 
	 */
	Page<AccountDetail> getDetailListByRecord(AccountDetail record);
}
