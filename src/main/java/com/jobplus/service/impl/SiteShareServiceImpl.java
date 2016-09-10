package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SiteShareMapper;
import com.jobplus.pojo.SiteShare;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.pojo.Page;
import com.jobplus.service.ISiteShareService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.DateUtils;

@Service("siteShareService")
public class SiteShareServiceImpl implements ISiteShareService{
	
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private SiteShareMapper siteShareDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return siteShareDao.deleteByPrimaryKey(id);
	}

	//插入一条站点评论  同时站点评论数 + 1
	@Transactional
	@Override
	public int insert(SiteShare record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sites_share");
		record.setId(id);
		ret = siteShareDao.insert(record);
		if(ret > 0){
			//对应站点的评论数 + 1
			updTableColumnService.updNums(5, 1, 0, 1, id);
		}
		return ret;
	}


	//删除一条站点评论   对应站点评论数 - 1
	@Transactional
	@Override
	public int delComment(SiteShare record) {
		int ret = 0;
		ret = siteShareDao.deleteByPrimaryKey(record.getId());
		if(ret > 0){
			//对应站点的评论数  - 1
			updTableColumnService.updNums(5, 1, 1, 1, record.getSiteid());
		}
		return ret;
	}

	@Override
	public Page<SiteShare> getList(SiteShare record) {
		Page<SiteShare> page = new Page<SiteShare>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<SiteShare> list = siteShareDao.getList(record);
		if (list.size() > 0) {
			for (SiteShare courseShare : list) {// 设置时间用于页面显示
				courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	//新增站点评论
	@Transactional
	@Override
	public SiteShare insertAndReturnRecord(SiteShare record,String contextPath,User user) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sites_share");
		record.setId(id);
		ret = siteShareDao.insert(record);
		if (ret > 0 &&  record.getSiteid()!=null) {
			// 对应站点的评论数 + 1
			updTableColumnService.updNums(5, 1, 0, 1, record.getSiteid());
			// 用于前端显示
			record.setUserCommentTime(DateUtils.formatDate(new java.sql.Date(new java.util.Date().getTime()), "yyyy-MM-dd"));
			
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[15],record.getSiteid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[17],record.getId(),record.getObjectNamePg());
			
			return record;
		} else {
			return null;
		}

	}
	@Override
	public int insertSelective(SiteShare record) {
		
		return siteShareDao.insertSelective(record);
	}

	@Override
	public SiteShare selectByPrimaryKey(Integer id) {
		
		return siteShareDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SiteShare record) {
		
		return siteShareDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SiteShare record) {
		
		return siteShareDao.updateByPrimaryKey(record);
	}


}
