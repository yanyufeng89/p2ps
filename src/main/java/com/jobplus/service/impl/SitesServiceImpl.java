package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.SitesMapper;
import com.jobplus.pojo.Sites;
import com.jobplus.pojo.SiteShare;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.service.ISiteShareService;
import com.jobplus.service.IAccountService;
import com.jobplus.service.IMyCollectService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISitesService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.SolrJUtils;

@Service("sitesService")
public class SitesServiceImpl implements ISitesService {

	@Resource
	private SitesMapper sitesDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUserService userService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private ISiteShareService siteShareService;
	@Resource
	private IMyCollectService myCollectService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private IAccountService accountService;

	@Transactional
	@Override
	public int insert(Sites record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sites");
		record.setId(id);
		ret = sitesDao.insert(record);
		if (ret > 0) {
			// operationSum表 站点分享数增加
			ret = operationSumService.updOperationSum(12, 0, 1,null);
			//增加财富值
			accountService.modAccountAndDetail(record.getUserid(), 0, new Account().getSCORES()[0], 
					1, 0, new Account().getSCORES()[0],5);
		}
		return ret;
	}

	// 同时 插入一条书籍分享记录
	@Transactional
	@Override
	public int insertSiteAndSiteShare(Sites record) {
		SiteShare share = new SiteShare();
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sites");
		int shareId = seqService.getSeqByTableName("tbl_sites_share");
		record.setId(id);
		share.setId(shareId);
		share.setUserid(record.getUserid());
		share.setSiteid(id);
		share.setRecommend(record.getRecommend());

		ret = sitesDao.insert(record);
		if (ret > 0) {
			// operationSum表 站点分享数增加
			ret = operationSumService.updOperationSum(12, 0, 1,null);
			// 同时 插入一条书籍分享记录
			ret = siteShareService.insert(share);
			// 初始默认值+1   对应站点的评论数 + 1
			updTableColumnService.updNums(5, 1, 0, 1, id);
			//增加财富值
			accountService.modAccountAndDetail(record.getUserid(), 0, new Account().getSCORES()[0], 
					1, 0, new Account().getSCORES()[0],5);
		}
		return ret;
	}

	// 我分享的站点列表
	@Override
	public Page<Sites> getSharedSiteList(Sites record) {
		Page<Sites> page = new Page<Sites>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());
		int count = sitesDao.getSharedSiteListCount(record);
		if(count < 1)
			return page;
		List<Sites> list = sitesDao.getSharedSiteList(record);
		if (list.size() > 0) {
			for (Sites course : list) {
				// 用于前端页面显示
				course.setUserShareTime(DateUtils.formatDate(course.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;

	}

	// 我收藏的站点列表
	@Override
	public Page<Sites> getCollectedSiteList(Sites record) {
		Integer userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		MyCollect collect = new MyCollect();
		collect.setUserid(userid);
		// 动作类型枚举 0下载 1收藏 ACTIONTYPE ={0,1}
		collect.setActionType(collect.getACTIONTYPES()[1]);
		// 类型 暂时用表名存储 tbl_docs tbl_topics tbl_books
		// COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books","tbl_sites","tbl_Articles","tbl_sites"};
		collect.setCollecttype(collect.getCOLLECTTYPES()[5]);
		record.setMyCollect(collect);

		Page<Sites> page = new Page<Sites>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());
		int count = sitesDao.getCollectedSiteListCount(record);
		if(count < 1)
			return page;
		List<Sites> list = sitesDao.getCollectedSiteList(record);
		if (list.size() > 0) {
			for (Sites course : list) {
				// 用于前端页面显示
				course.setUserShareTime(DateUtils.formatDate(course.getMyCollect().getColltime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}

		return page;
	}

	// 批量删除个人中心-我分享的站点
	@Transactional
	@Override
	public int delSharedSites(String condition[]) {
		int ret = 0;
		ret = sitesDao.delSharedSites(condition);
		if (ret > 0) {
			operationSumService.updOperationSum(12, 1, condition.length,null);
		}
		return ret;
	}

	// 获取站点详情 浏览数++
	@Override
	public Sites getSiteDetail(Sites record) {
		// 1.站点主体
		record = sitesDao.selectByRecord(record);
		if(null == record){
			return null;
		}
		// 浏览次数+1
		sitesDao.updateReadSum(record.getId());

		// 2.收藏此站点人的列表
		// 设置 收藏还是下载 MyCollect（ 动作类型枚举 0下载 1收藏）ACTIONTYPE ={0,1}
		// 设置表名 MyCollect COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_articles", "tbl_sites" };
		List<User> userList = userService.getCollectUsers(new MyCollect().getCOLLECTTYPES()[5],
				new MyCollect().getACTIONTYPES()[1], record.getId());

		// 3.相关站点列表 
		@SuppressWarnings({ "static-access", "unchecked" })
		List<Sites> theSameSites = solrJUtils.findSitesFromList(record.getTitle(), record.getId().toString(),record.getSitetype(),record.getSiteclass());
		
		
		// 4.评论列表
		SiteShare share = new SiteShare();
		share.setSiteid(record.getId());

		Page<SiteShare> shareList = siteShareService.getList(share);

		record.setCollectUsers(userList);
		record.setRelatedList(theSameSites);
		record.setCommentList(shareList);
		return record;
	}

	@Transactional
	@Override
	public MyCollect collectSite(MyCollect record) {
		int ret = 0;
		if (record.getJudgeTodo() == 0) {
			// 开始收藏站点
			int id = seqService.getSeqByTableName("tbl_collect");
			record.setId(id);
			record.setActionType(record.getACTIONTYPES()[1]);// 动作类型枚举 0下载 1收藏
			record.setCollecttype(record.getCOLLECTTYPES()[5]);// 类型 暂时用表名存储
																// "tbl_docs","tbl_topics","tbl_books","tbl_sites","tbl_Articles","tbl_sites"
			// 插入记录
			ret = myCollectService.insert(record);

			// 对应用户站点收藏数 增加
			operationSumService.updOperationSum(13, 0, 1,null);

			// 站点的收藏数增加
			updTableColumnService.updNums(5, 0, 0, 1, record.getObjectid());

		} else if (record.getJudgeTodo() == 1) {
			// 取消收藏站点
			// 删除记录

			if (record.getCollecttype() != null && record.getObjectid() != null && record.getActionType() != null
					&& record.getUserid() != null) {
				ret = myCollectService.delMycollects(record);
			}

			// 对应用户站点收藏数 减少
			operationSumService.updOperationSum(13, 1, 1,null);

			// 站点的收藏数减少
			updTableColumnService.updNums(5, 0, 1, 1, record.getObjectid());
		}
		if(ret  < 1){
			record = null;
		}
		return record;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return sitesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Sites record) {
		
		return sitesDao.insertSelective(record);
	}

	@Override
	public Sites selectByPrimaryKey(Integer id) {
		
		return sitesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Sites record) {
		
		return sitesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Sites record) {
		
		return sitesDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Sites record) {
		
		return sitesDao.updateByPrimaryKey(record);
	}

}
