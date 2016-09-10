package com.jobplus.service.impl;

import com.jobplus.dao.OauthLoginInfoMapper;
import com.jobplus.dao.UserMapper;
import com.jobplus.pojo.OauthLoginInfo;
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
    public User getUserFromOauth(OauthLoginInfo record) {
        OauthLoginInfo loginInfo = oauthLoginInfoMapper.selectByNameAndOpenId(record);
        User loginParam = new User();
        User user = new User();
        if (loginInfo == null) {
            int userId = seqService.getSeqByTableName("tbl_user");
            record.setUserid(userId);
            user.setUserid(userId);
            user.setUsername(record.getNickname());
            userMapper.insertSelective(user);
            int ID = seqService.getSeqByTableName("tbl_oauth_login");
            record.setId(ID);
            oauthLoginInfoMapper.insert(record);
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
