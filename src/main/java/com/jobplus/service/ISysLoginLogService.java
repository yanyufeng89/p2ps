package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;

import com.jobplus.pojo.SysLoginLog;
import com.jobplus.pojo.User;

public interface ISysLoginLogService {
	
	   int deleteByPrimaryKey(Integer id);

	    int insert(HttpServletRequest request, User user);

	    int insertSelective(SysLoginLog record);

	    SysLoginLog selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(SysLoginLog record);

	    int updateByPrimaryKey(SysLoginLog record);

}
