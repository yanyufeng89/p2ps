package com.jobplus.service.impl;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.PvLogDao;
import com.jobplus.pojo.LogBean;
import com.jobplus.service.PvLogService;

@Service("pvService")
public class PvLogServiceImpl implements PvLogService {
	
	@Resource
	private PvLogDao pvDao;

	public void setLogDao(PvLogDao pvDao) {
		this.pvDao = pvDao;
	}

	
	public LogBean getLatestLog(String sessionId, String ip)
			throws Exception {
		return pvDao.getLatestLog(sessionId, ip);
	}

	@Transactional
	public void saveLog(LogBean logBean) throws Exception {
		pvDao.saveLog(logBean);
	}

	@Transactional
	public void updateLog(String id, long stayTime) throws Exception {
		pvDao.updateLog(id, stayTime);
	}

	
	public int getPV(Timestamp startTime, Timestamp endTime) throws Exception {
		return pvDao.getPV(startTime, endTime);
	}


	public int getUV(Timestamp startTime, Timestamp endTime) throws Exception {
		return pvDao.getUV(startTime, endTime);
	}

}
