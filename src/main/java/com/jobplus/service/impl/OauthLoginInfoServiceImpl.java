package com.jobplus.service.impl;

import com.jobplus.dao.AccountMapper;
import com.jobplus.dao.OauthLoginInfoMapper;
import com.jobplus.dao.OperationSumMapper;
import com.jobplus.dao.UserMapper;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.OauthLoginInfo;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.User;
import com.jobplus.service.IOauthLoginInfoService;
import com.jobplus.service.ISequenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("oauthLoginInfoService")
public class OauthLoginInfoServiceImpl implements IOauthLoginInfoService {

    @Resource
    private OauthLoginInfoMapper oauthLoginInfoMapper;
    @Resource
    private ISequenceService seqService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountMapper accountDao;
    @Resource
    private OperationSumMapper operationSumMapperDao;

    @Transactional
    @Override
    public int insert(OauthLoginInfo record) {
        User user = new User();
        int userId = seqService.getSeqByTableName("tbl_user");
        record.setUserid(userId);
        user.setUserid(userId);
        user.setUsername(record.getNickname());
        userMapper.insertSelective(user);
        int ID = seqService.getSeqByTableName("tbl_oauth_login");
        record.setId(ID);
        return oauthLoginInfoMapper.insert(record);
    }

    @Transactional
    @Override
    public int insertSelective(OauthLoginInfo record) {
        return oauthLoginInfoMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(OauthLoginInfo record) {
        return oauthLoginInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public OauthLoginInfo selectByNameAndOpenId(OauthLoginInfo record) {
        return oauthLoginInfoMapper.selectByNameAndOpenId(record);
    }

    @Transactional
    @Override
    public User getUserFromOauth(OauthLoginInfo loginInfo, OauthLoginInfo record) {
        User loginParam = new User();
        User user = new User();
        if (loginInfo == null) {
            java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
            int userId = seqService.getSeqByTableName("tbl_user");
            record.setUserid(userId);
            user.setUserid(userId);
            user.setUsername(record.getNickname());
            user.setHeadicon(record.getHeadicon());
            user.setIsvalid(1);
            user.setCreatetime(time);
            user.setUpdatetime(time);
            userMapper.insertSelective(user);
            int ID = seqService.getSeqByTableName("tbl_oauth_login");
            record.setId(ID);
            oauthLoginInfoMapper.insert(record);
            // 初始化账户信息
            Account account = new Account();
            account.setId(seqService.getSeqByTableName("tbl_account"));
            account.setUserid(userId);
            account.setFreezeup(0);
            account.setJiamoney(0);
            account.setPoints(0);
            account.setCreatetime(time);
            accountDao.insert(account);

            // 初始化用户操作统计表
            OperationSum opSum = new OperationSum();
            opSum.setUserid(userId);
            opSum.setOperatortime(time);
            operationSumMapperDao.insert(opSum);
        } else {
            user = userMapper.selectByPrimaryKey(loginInfo.getUserid());
        }
        if (user != null && StringUtils.isNotBlank(user.getPasswd())) {
            return user;
        } else {
            loginParam.setUsername(String.valueOf(user.getUserid()));
            return loginParam;
        }
    }
}
