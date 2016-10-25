package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.ArticleShareMapper;
import com.jobplus.pojo.ArticleShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IArticleShareService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.DateUtils;

@Service("articleShareService")
public class ArticleShareServiceImpl implements IArticleShareService {
	@Resource
	private ArticleShareMapper articleShareDao;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	// 插入一条文章评论 同时文章评论数 + 1
	@Transactional
	@Override
	public int insert(ArticleShare record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_article_share");
		record.setId(id);
		ret = articleShareDao.insert(record);
		if (ret > 0) {
			// 对应文章的评论数 + 1
			updTableColumnService.updNums(4, 1, 0, 1, id);
		}
		return ret;
	}

	// 删除一条文章评论 对应文章评论数 - 1
	@Transactional
	@Override
	public int delComment(ArticleShare record) {
		int ret = 0;
		ret = articleShareDao.deleteByPrimaryKey(record.getId());
		if (ret > 0) {
			// 对应文章的评论数 - 1
			updTableColumnService.updNums(4, 1, 1, 1, record.getArticleid());
		}
		return ret;
	}

	@Override
	public Page<ArticleShare> getList(ArticleShare record) {
		Page<ArticleShare> page = new Page<ArticleShare>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = articleShareDao.getListCount(record);
		if(count < 1)
			return page;
		List<ArticleShare> list = articleShareDao.getList(record);
		if (list.size() > 0) {
			for (ArticleShare courseShare : list) {// 设置时间用于页面显示
				courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	@Transactional
	@Override
	public ArticleShare insertAndReturnRecord(ArticleShare record,String contextPath,User user) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_article_share");
		record.setId(id);
		ret = articleShareDao.insert(record);
		if (ret > 0 && record.getArticleid() != null) {
			// 对应文章的评论数 + 1
			updTableColumnService.updNums(4, 1, 0, 1, record.getArticleid());
			// 用于前端显示
			record.setUserCommentTime(
					DateUtils.formatDate(new java.sql.Date(new java.util.Date().getTime()), "yyyy-MM-dd"));
			
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[13],record.getArticleid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[14],record.getId(),record.getObjectNamePg(),"");
			
			return record;
		} else {
			return null;
		}

	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return articleShareDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ArticleShare record) {
		
		return articleShareDao.insertSelective(record);
	}

	@Override
	public ArticleShare selectByPrimaryKey(Integer id) {
		
		return articleShareDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleShare record) {
		
		return articleShareDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ArticleShare record) {
		
		return articleShareDao.updateByPrimaryKey(record);
	}

}
