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
import com.jobplus.service.ISmsService;
import com.jobplus.utils.ConstantManager;
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
    @Resource
    private ISmsService smsService;

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
    public User getUserFromOauth(OauthLoginInfo loginInfo, OauthLoginInfo record) throws Exception {
        User loginParam = new User();
        User user = new User();
        java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
        if (loginInfo == null) {
            int userId = seqService.getSeqByTableName("tbl_user");
            record.setUserid(userId);
            user.setUserid(userId);
            //用户名去重
            int index = userMapper.countUserName(record.getNickname());
            if (index == 0)
                user.setUsername(record.getNickname());
            else
                user.setUsername(record.getNickname() + "_" + (index + 1));
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

            //发送短信
            smsService.sendSysNotice(userId,ConstantManager.FIRST_LOGIN_SMS);
        } else {
            user = userMapper.selectByPrimaryKey(loginInfo.getUserid());
        }
        if (user == null || user.getUserid() == null)
            throw new Exception("*****获取本地账号失败****" + loginInfo.getUserid() + "用户不存在");
        else {
            if (StringUtils.isNotBlank(user.getPasswd())) {
                return user;
            } else {
                loginParam.setUsername(String.valueOf(user.getUserid()));
                return loginParam;
            }
        }
    }
}
