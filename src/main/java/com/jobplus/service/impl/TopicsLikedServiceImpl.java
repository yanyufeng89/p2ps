package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.TopicsLikedMapper;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.TopicsLiked;
import com.jobplus.pojo.User;
import com.jobplus.service.ISmsService;
import com.jobplus.service.ITopicsCommentService;
import com.jobplus.service.ITopicsLikedService;
import com.jobplus.service.IUpdTableColumnService;

@Service("topicsLikedService")
public class TopicsLikedServiceImpl implements ITopicsLikedService {

	@Resource
	private TopicsLikedMapper topicsLikedDao;
	@Resource
	private ITopicsCommentService topicsCommentService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(TopicsLiked record) {		
		int ret = topicsLikedDao.deleteByPrimaryKey(record);
		// 对应话题评论的点赞个数减1
		if (record.getCommid() != null && ret >0) {
			ret = updTableColumnService.updNums(8, 1, 1, 1, record.getCommid());
		}
		return ret;
	}

	@Transactional
	@Override
	public int insert(TopicsLiked record,String contextPath,User user) {		
		int ret = topicsLikedDao.insert(record);
		// 对应话题评论的点赞个数增加
		if (record.getCommid() != null && ret>0) {
			ret = updTableColumnService.updNums(8, 1, 0, 1, record.getCommid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[10],record.getTopObjId(),
					record.getObjCreatepersonPg(),15, record.getRelationidPg(),record.getObjectNamePg(),"");
			
		}
		return ret;
	}

	@Transactional
	@Override
	public int insertSelective(TopicsLiked record) {
		
		return topicsLikedDao.insertSelective(record);
	}

}
