package com.jobplus.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.CpnewsCommentMapper;
import com.jobplus.dao.CpnewsIslikedMapper;
import com.jobplus.pojo.CpnewsComment;
import com.jobplus.pojo.CpnewsIslikedKey;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.ICpnewsCommentService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("cpnewsCommentServie")
public class CpnewsCommentServieImpl implements ICpnewsCommentService {

	@Resource
	private CpnewsCommentMapper cpnewsCommentDao;
	@Resource
	private CpnewsIslikedMapper cpnewsIslikedDao;

	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;
	
	@Override
	public Page<CpnewsComment> getComtList(CpnewsComment record) {
		Page<CpnewsComment> page = new Page<CpnewsComment>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = cpnewsCommentDao.getListCount(record);
		if (count < 1)
			return page;
		List<CpnewsComment> list = cpnewsCommentDao.getList(record);
		if (list.size() > 0) {
			/*
			 * for (CpnewsComment courseShare : list) {// 设置时间用于页面显示
			 * courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.
			 * getCreatetime(), "yyyy-MM-dd")); }
			 */
			page.initialize((long) count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	@Transactional
	@Override
	public CpnewsComment insertAndReturnRecord(CpnewsComment record,String contextPath,User user) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_cpnews_comment");
		record.setId(id);
		record.setUpdatetime(new Date());
		record.setCreatetime(new Date());
		record.setIsdelete(0);
		ret = cpnewsCommentDao.insert(record);
		if(ret > 0){
			//对应快讯评论条数+1
			updTableColumnService.updNums(11, 0, 0, 1, record.getNewsid());
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[20],record.getNewsid(),
					record.getObjCreatepersonPg(),1000,record.getId(),record.getObjectNamePg(),"");
			return record;
			
		}
		
		return null;
	}

	@Override
	public int delComment(CpnewsComment record) {
		int ret = 0;
		record.setIsdelete(1);
		ret = cpnewsCommentDao.updateByPrimaryKeySelective(record);
		if(ret>0){
			//对应快讯评论条数-1
			updTableColumnService.updNums(11, 0, 1, 1, record.getNewsid());
		}
		return ret;
	}
	@Override
	public int likeNews(CpnewsIslikedKey record,String contextPath,User user)  {
		int ret = 0;
		ret = cpnewsIslikedDao.insert(record);
		if(ret>0){
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[20],record.getNewsid(),
					record.getObjCreatepersonPg(),1000,record.getNewsid(),record.getObjectNamePg(),"");
			
			//对应快讯点赞数+1
			updTableColumnService.updNums(11, 1, 0, 1, record.getNewsid());
		}
		return ret;
	}

	@Override
	public int cancelLikeNews(CpnewsIslikedKey record) {
		int ret = 0;
		ret = cpnewsIslikedDao.deleteByPrimaryKey(record);
		if(ret>0){
			//对应快讯点赞数-1
			updTableColumnService.updNums(11, 1, 1, 1, record.getNewsid());
		}
		return ret;
	}

	
	
	
}
