package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.VisitHistoryMapper;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.VisitHistory;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.IVisitHistoryService;
import com.jobplus.utils.DateUtils;

@Service("visitHistoryService")
public class VisitHistoryServiceImpl implements IVisitHistoryService {
	@Resource
	private VisitHistoryMapper visitHistoryDao;
	@Resource
	private ISequenceService seqService;

	
	@Override
	public int insert(VisitHistory record) {
		int id = seqService.getSeqByTableName("tbl_visitHistory");
		record.setId(id);
		if(record.getVisitorid().intValue() == record.getUserid().intValue()){
			//自己访问自己主页
			return 1;
		}
		return visitHistoryDao.insert(record);
	}
	@Transactional
	@Override
	public int insertOrUpdate(VisitHistory record) {
		int id = seqService.getSeqByTableName("tbl_visitHistory");
		record.setId(id);
		if(record.getVisitorid().intValue() == record.getUserid().intValue()){
			//自己访问自己主页
			return 1;
		}
		return visitHistoryDao.insertOrUpdate(record);
	}

	@Override
	public int insertSelective(VisitHistory record) {
		
		return visitHistoryDao.insertSelective(record);
	}

	//最近访问我的主页的人
	@Override
	public Page<VisitHistory> getRecentVistors(VisitHistory record) {
		Page<VisitHistory> page = new Page<VisitHistory>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = visitHistoryDao.getRecentVistorsCount(record); 
		if(count < 1)
			return page;
		List<VisitHistory> list = visitHistoryDao.getRecentVistors(record);
		if (list.size() > 0) {
			for (VisitHistory vh : list) {// 设置时间用于页面显示
				vh.setShowVTime(DateUtils.formatDate(vh.getVisittime(), "yyyy-MM-dd HH:mm:ss"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}
	
	
	
}
