package com.jobplus.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jobplus.pojo.LogBean;
import com.jobplus.service.ISequenceService;
import com.jobplus.utils.DBUtilsTemplate;

@Service("pvDao")
public class PvLogDaoImpl implements PvLogDao {
	

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PvLogDaoImpl.class);
	
	@Resource
	private DBUtilsTemplate dBUtilsTemplate;
	
	@Resource
	private ISequenceService seqService;
	
	
	public void saveLog(LogBean logBean) throws Exception {
		String sql = "insert into tbl_syspvlog(id, sessionid, ip, page, accesstime, staytime) values(?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[]{seqService.nextVal("tbl_syspvlog"), logBean.getSessionId(), 
				logBean.getIp(), logBean.getPage(), logBean.getAccessTime(), logBean.getStayTime()};
		
		logger.info(sql);
		this.dBUtilsTemplate.update(sql, params);
	}
	
	public LogBean getLatestLog(String sessionId, String ip)
			throws Exception {
		String sql = "select * from tbl_syspvlog where sessionid = ? and ip = ? order by accesstime desc limit 0, 1 ";
		logger.info(sql);
		
		List<LogBean> list = this.dBUtilsTemplate.find(LogBean.class,sql, new Object[]{sessionId, ip});
		
		LogBean logBean = null;
		if(list != null && !list.isEmpty()){
			logBean = list.get(0);
		}
		return logBean;
	}

	public void updateLog(String id, long stayTime) throws Exception {
		String sql = "update tbl_syspvlog set staytime = ? where id = ? ";
		logger.info(sql);
		
		Object[] params = new Object[]{stayTime, id};
		this.dBUtilsTemplate.update(sql, params);
	}

	public int getPV(Date startTime, Date endTime) throws Exception {
		String sql = "select count(*) from tbl_syspvlog where accesstime >= ? and accesstime < ? ";
		logger.info(sql);
		
		Long pv = (Long)this.dBUtilsTemplate.findBy(sql, 1,new Object[]{startTime, endTime});
		return Integer.parseInt(String.valueOf(pv));
	}

	public int getUV(Date startTime, Date endTime) throws Exception {
		String sql = "select count(ip) from( " +
				"select distinct ip from tbl_syspvlog where accesstime >= ? and accesstime < ? " +
				") log_temp ";
		logger.info(sql);
		
		Long uv = (Long)this.dBUtilsTemplate.findBy(sql, 1,new Object[]{startTime, endTime});
		return Integer.parseInt(String.valueOf(uv));
	}

}
