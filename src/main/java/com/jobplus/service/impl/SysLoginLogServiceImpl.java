package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SysLoginLogMapper;
import com.jobplus.pojo.SysLoginLog;
import com.jobplus.pojo.User;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISysLoginLogService;

@Service("sysLoginLogService")
public class SysLoginLogServiceImpl implements ISysLoginLogService {

	@Resource
	private SysLoginLogMapper sysLoginLogMapper;
	@Resource
	private ISequenceService seqService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		

		return sysLoginLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(HttpServletRequest request, User user) {
		
		SysLoginLog record = new SysLoginLog();
		int ret = 0;
		int logId = seqService.getSeqByTableName("tbl_sysLoginLog");
		record.setId(logId);
		record.setUserid(user.getUserid());
		record.setLogintime(new java.sql.Timestamp(System.currentTimeMillis()));
		record.setSessionid(request.getSession().getId());
		record.setUserip(getRemoteAddress(request));
		record.setLoginmode(1);
		record.setUrl(request.getRequestURL().toString());
		record.setServiceip(request.getLocalAddr());
		ret = sysLoginLogMapper.insert(record);
		return ret;
	}

	public static String getRemoteAddress(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public int insertSelective(SysLoginLog record) {
		
		int ret = 0;
		int logId = seqService.getSeqByTableName("tbl_sysLoginLog");
		record.setId(logId);
		ret = sysLoginLogMapper.insert(record);
		return ret;
	}

	@Override
	public SysLoginLog selectByPrimaryKey(Integer id) {
		
		return sysLoginLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysLoginLog record) {
		
		return sysLoginLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysLoginLog record) {
		
		return sysLoginLogMapper.updateByPrimaryKey(record);
	}

}
