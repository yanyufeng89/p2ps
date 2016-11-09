package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.ArticleLikedMapper;
import com.jobplus.pojo.ArticleLiked;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IArticleLikedService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("articleLikedService")
public class ArticleLikedServiceImpl implements IArticleLikedService {
	@Resource
	private ArticleLikedMapper articleLikedDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(ArticleLiked key) {
		int ret = 0;
		ret = articleLikedDao.deleteByPrimaryKey(key);
		if (ret > 0) {
			// 文章点赞数 - 1
			updTableColumnService.updNums(4, 2, 1, 1, key.getArticleid());
		}
		return ret;
	}

	@Transactional
	@Override
	public int insert(ArticleLiked record,String contextPath,User user) {
		int ret = 0;
		ret = articleLikedDao.insert(record);
		if (ret > 0) {
			// 文章点赞数 + 1
			updTableColumnService.updNums(4, 2, 0, 1, record.getArticleid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[14],record.getArticleid(),
					record.getObjCreatepersonPg(),43,record.getRelationidPg(),record.getObjectNamePg(),"");
			
		}
		return ret;
	}

	@Transactional
	@Override
	public int insertSelective(ArticleLiked record) {
		
		return articleLikedDao.insertSelective(record);
	}

}
