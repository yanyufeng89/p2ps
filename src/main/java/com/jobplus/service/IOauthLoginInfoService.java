package com.jobplus.service;

import com.jobplus.pojo.OauthLoginInfo;
import com.jobplus.pojo.User;

public interface IOauthLoginInfoService {

    int insert(OauthLoginInfo record);

    int insertSelective(OauthLoginInfo record);

    int updateByPrimaryKeySelective(OauthLoginInfo record);

    /**
     * 根据类型和openID查询
     *
     * @param record
     * @return
     */
    OauthLoginInfo selectByNameAndOpenId(OauthLoginInfo record);

    /**
     * 根据第三方登录获取本地用户信息
     *
     * @param record
     * @return
     */
    User getUserFromOauth(OauthLoginInfo record);
}
