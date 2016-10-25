package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.BookLikedMapper;
import com.jobplus.pojo.BookLiked;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IBookLikedService;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("bookLikedService")
public class BookLikedServiceImpl implements IBookLikedService{

	@Resource
	private BookLikedMapper bookLikedDao;
	@Resource
	private IBookShareService bookShareService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(BookLiked record) {
		// 对应书籍评论的点赞个数减少
		if (record.getCommid() != null) {
			//对应书籍评论的点赞个数减少
			updTableColumnService.updNums(7, 0, 1, 1, record.getCommid());
		}
		return bookLikedDao.deleteByPrimaryKey(record);
	}

	@Transactional
	@Override
	public int insert(BookLiked record,String contextPath,User user) {

		// 对应书籍评论的点赞个数增加
		if (record.getCommid() != null) {
			//对应书籍评论的点赞个数增加
			updTableColumnService.updNums(7, 0, 0, 1, record.getCommid());
			
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[8], record.getTopObjId(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[9],record.getRelationidPg(),record.getObjectNamePg(),"");
		}
		return bookLikedDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(BookLiked record) {
		
		return bookLikedDao.insertSelective(record);
	}
	

}
