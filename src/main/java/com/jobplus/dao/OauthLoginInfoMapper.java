package com.jobplus.dao;

import com.jobplus.pojo.OauthLoginInfo;

import java.util.List;

public interface OauthLoginInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OauthLoginInfo record);

    int insertSelective(OauthLoginInfo record);

    OauthLoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OauthLoginInfo record);

    int updateByPrimaryKey(OauthLoginInfo record);

    OauthLoginInfo selectByNameAndOpenId(OauthLoginInfo record);

    List<OauthLoginInfo> findByUserId(int userid);
}