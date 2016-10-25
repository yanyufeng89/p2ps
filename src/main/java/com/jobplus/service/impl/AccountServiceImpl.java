package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.AccountMapper;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.AccountDetail;
import com.jobplus.service.IAccountDetailService;
import com.jobplus.service.IAccountService;

/**
 * 账户
 * 
 * @author Jerry 2016年6月27日上午10:20:52
 *
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	@Resource
	private IAccountDetailService accountDetailService;
	@Resource
	private AccountMapper accountDao;

	@Override
	public Account getAccountByUserId(int userid) {
		Account account = new Account();
		account = accountDao.getAccountByUserId(userid);

		return account;
	}

	@Override
	public int addPointsOrAddJiaMoney(Account record) {
		int ret = 0;
		ret = accountDao.addPointsOrAddJiaMoney(record);
		return ret;
	}

	/**
	 * 增加或减少账户金额(加币或者积分),同时记录详情(AccountDetail)
	 * 
	 * @param account
	 * @return
	 */
	@Transactional
	@Override
	public int adOrDecAccount(Account account, AccountDetail accountDetail) {
		int ret = 0;
		ret = accountDao.adOrDecAccount(account);
		if (ret > 0) {
			accountDetailService.insertAccountDetail(accountDetail);
		}
		return ret;
	}
	/**
	 * 更改用户积分并记录详情                  Account &&  AccountDetail
	 * @param userid		Account
	 * @param moneyType	    Account        MONEYTYPES = {"points","jiaMoney"};
	 * @param score        Account        []SCORES = {1,2,3,4,5,6,7,8,9,10,15,20};
	 * @param changeitem    AccountDetail  CHANGEITEMS = {"jiaMoney","points"};
	 * @param changetype    AccountDetail  CHANGETYPES = {"1","2"}; 1增加  2减少
	 * @param changevalue   AccountDetail  []CHANGEVALUES = {1,2,3,4,5,6,7,8,9,10,15,20};
	 * @param changecause   AccountDetail  CHANGECAUSES =  {"分享文档","分享话题","分享书籍","分享课程","分享文章","分享站点","下载文档","回答问题","文档被下载","打赏文章","文章被打赏"}
	 * @return
	 */
	@Override
	public int modAccountAndDetail(Integer userid, Integer moneyType, Integer score
			, Integer changeitem,Integer changetype, Integer changevalue, Integer changecause) {
		int ret = 0;
		Account act = new Account();
        act.setUserid(userid);
        act.setMoneyType(act.getMONEYTYPES()[moneyType]);
        act.setScore(score);
        
        AccountDetail actd = new AccountDetail();
        actd.setChangeitem(actd.getCHANGEITEMS()[changeitem]);
        actd.setChangetype(actd.getCHANGETYPES()[changetype]);
        actd.setChangevalue(changevalue);
      //CHANGECAUSE = {"shareDoc","shareTopic","shareBook","shareCourse","shareArticle","shareSite",""};
        actd.setChangecause(actd.getCHANGECAUSES()[changecause]);
        int accountid = this.getAccountByUserId(userid).getId();
        actd.setAccountid(accountid);
        
        ret = this.adOrDecAccount(act, actd);
		return ret;
	}

}
