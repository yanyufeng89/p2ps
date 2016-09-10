package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.AccountDetailMapper;
import com.jobplus.pojo.AccountDetail;
import com.jobplus.pojo.Page;
import com.jobplus.service.IAccountDetailService;
import com.jobplus.service.ISequenceService;
import com.jobplus.utils.DateUtils;

@Service("accountDetailService")
public class AccountDetailServiceImpl implements IAccountDetailService {
	@Resource
	private AccountDetailMapper accountDetailDao;
	@Resource
	private ISequenceService seqService;

	@Transactional
	@Override
	public int insertAccountDetail(AccountDetail record) {
		int ret = 0;
		int detailID = seqService.getSeqByTableName("tbl_accountDetail");
		record.setId(detailID);
		ret = accountDetailDao.insert(record);
		return ret;
	}

	/**
	 * 获取账户详情列表 page
	 */
	@Override
	public Page<AccountDetail> getDetailListByRecord(AccountDetail record) {
		Page<AccountDetail> page = new Page<AccountDetail>();
	    record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
	    record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
	    record.setPageSize(page.getPageSize());
	    List<AccountDetail> list = accountDetailDao.getListByRecord(record);
	    if (list.size() > 0) {
	      for (AccountDetail act : list) {
	        // 用于前端页面显示
	        act.setShowCreateTime(DateUtils.formatDate(act.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
	      }
	      page.initialize(list.get(0).getPageCount(), record.getPageNo());
	      page.setList(list);
	    }
	    return page;
	}

}
