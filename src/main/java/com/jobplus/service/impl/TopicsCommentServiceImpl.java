package com.jobplus.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.TopicsCommentMapper;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.TopicsComment;
import com.jobplus.pojo.User;
import com.jobplus.service.IAccountDetailService;
import com.jobplus.service.IAccountService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.ITopicsCommentService;
import com.jobplus.service.ITopicsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.DateUtils;

@Service("topicsCommentService")
public class TopicsCommentServiceImpl implements ITopicsCommentService {

	@Resource
	private TopicsCommentMapper topicsCommentDao;
	@Resource
	private ITopicsService topicsService;
	@Resource
	private ITopicsCommentService topicsCommentService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IAccountService accountService;
	@Resource
	private IAccountDetailService accountDetailService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	
	/**
	 * 我回复过的话题
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Page<TopicsComment> getMyTopicsComments(TopicsComment record) {
		Page<TopicsComment> page = new Page<TopicsComment>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<TopicsComment> list = topicsCommentDao.getMyTopicsComments(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
			for (TopicsComment topicsComment : list) {
				topicsComment.setShowcreatetime(DateUtils.formatDate(topicsComment.getCreatetime(), "yyyy-MM-dd"));
			}
		}
		return page;
	}

	/**
	 * 个人中心--批量删除我的回复
	 * 
	 * @param condition
	 * @return
	 */
	@Transactional
	@Override
	public int deleteTopicsComments(String[] condition, String userid) {
		int ret = 0;
		ret = topicsCommentDao.deleteTopicsComments(condition);
		if(ret > 0){
			// 对应用户操作话题回复数减少             topicsComSum
			ret = operationSumService.updOperationSum(4, 1, condition.length,null);
		}
		return ret;
	}
	
	/**
	 * 获取话题的评论 或者  话题下回答的评论
	 * @param topicsComment
	 * @return
	 */
	@Override
	public Page<TopicsComment> getPartTopicsComments(TopicsComment record) {
		Page<TopicsComment> page = new Page<TopicsComment>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<TopicsComment> list = topicsCommentDao.getPartTopicsComments(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
			for (TopicsComment topicsComment2 : list) {
				topicsComment2.setShowcreatetime(DateUtils.formatDate(topicsComment2.getCreatetime(), "yyyy-MM-dd"));
			}
		}
		return page;
	}

	@Transactional
    @Override
	public TopicsComment insert(TopicsComment record, String contextPath, User user) {
		int ret = 0;
		int userID = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));// record.getUserid();
		int comentID = seqService.getSeqByTableName("tbl_topicsComment");
		record.setId(comentID);
		record.setUserid(userID);
		// 用于返回给页面端显示 故在此初始化
		record.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		record.setUpdatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		record.setShowcreatetime(DateUtils.formatDate(record.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));

		// 1.插入话题回答
		ret = topicsCommentDao.insert(record);
		if (ret > 0) {
			// 2.分享话题成功 增加积分 3.记录积分动作

			accountService.modAccountAndDetail(userID, 0, new Account().getSCORES()[0], 1, 0,
					new Account().getSCORES()[0], 1);

			if (record.getType() != null) {
				if (record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_REPLY) {// 话题回答+1
					// 1 对应用户话题回答数 增加
					operationSumService.updOperationSum(4, 0, 1,null);

					// 2 更新Topics replySum
					if (record.getTopicsid() != null) {
						updTableColumnService.updNums(2, 2, 0, 1, record.getTopicsid());
					}

					// 添加消息通知
					smsService.addNotice(user, contextPath, new Sms().getTABLENAMES()[9], record.getTopicsid(),
							record.getObjCreatepersonPg(), new Sms().getSMSTYPES()[3], record.getId(),
							record.getObjectNamePg());

				} else if (record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_REPLYCOMMENT) {// 回答的评论+1
					if (record.getTopicsid() != null) {
						// tableColumn : TOPICSCOMMENTCOLUMNS = new String[]{
						// "replySum","likeSum","collectSum"};
						updTableColumnService.updNums(8, 0, 0, 1, record.getParentcommid());

						// 添加消息通知
						smsService.addNotice(user, contextPath, new Sms().getTABLENAMES()[9], record.getTopicsid(),
								record.getObjCreatepersonPg(), new Sms().getSMSTYPES()[6], record.getId(),
								record.getObjectNamePg());

					}
				} else if (record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_TOPICCOMMENT) {// 话题的评论+1
					if (record.getTopicsid() != null) {
						// tableColumn : TOPICSCOLUMNS = new
						// String[]{"followsSum","readSum","replySum","likeSum","futilitySum","collectSum","commentSum"};
						updTableColumnService.updNums(2, 6, 0, 1, record.getTopicsid());

						// 添加消息通知
						smsService.addNotice(user, contextPath, new Sms().getTABLENAMES()[9], record.getTopicsid(),
								record.getObjCreatepersonPg(), new Sms().getSMSTYPES()[5], record.getId(),
								record.getObjectNamePg());

					}
				}
			}

		} else {
			record = null;
		}

		return record;
	}
	/**
	 * 在话题详情页删除 话题评论、话题回复、回答的评论   //回复类型    回答1 回答评论2   话题评论3 
	 * @param topicsComment
	 * @return
	 */
	@Transactional
	@Override
	public int delCommentOnTopDetail(TopicsComment record) {
//		int userID = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		int ret = 0;
		if(record.getType() != null){
        	if(record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_REPLY){//话题回答-1 
        		//1  对应用户话题回答数   Topicscomsum
                operationSumService.updOperationSum(4, 1, 1,null);
                //2 更新Topics replySum
                if(record.getTopicsid() != null){
                	updTableColumnService.updNums(2, 2, 1, 1, record.getTopicsid());                	
                }
                
        	}else if(record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_REPLYCOMMENT){//回答的评论-1
        		if(record.getTopicsid() != null){
                	updTableColumnService.updNums(8, 0, 1, 1, record.getParentcommid());
                }              
        	}else if(record.getType() == ConstantManager.TBL_TOPICSCOMMENT_TYPE_TOPICCOMMENT){//话题的评论-1
        		if(record.getTopicsid() != null){
        			updTableColumnService.updNums(2, 6, 1, 1, record.getTopicsid());                	
                }
        	}        	
        	//逻辑删除记录
        	record.setIsdelete(1);
        	ret = topicsCommentService.updateByPrimaryKeySelective(record);
        }
		return ret;
	}

	@Override
	public int updateByPrimaryKeySelective(TopicsComment record) {
		int ret = 0;
		ret = topicsCommentDao.updateByPrimaryKeySelective(record);
		return ret;
	}

	/**
     * 点赞  取消 点赞 
     * @param record
     * @return
     */
	@Transactional
	@Override
	public int updateLikeSum(TopicsComment record) {
		int ret = 0;
		ret = topicsCommentDao.updateLikeSum(record);
		return ret;
	}

	@Override
	public TopicsComment selectByPrimaryKey(Integer id) {
		
		return topicsCommentDao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return topicsCommentDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(TopicsComment record) {
		
		return topicsCommentDao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TopicsComment record) {
		return topicsCommentDao.updateByPrimaryKeyWithBLOBs(record);
	}
	@Override
	public Page<TopicsComment> getSortTopicsCommentsByTopicId(TopicsComment record) {
		Page<TopicsComment> page = new Page<TopicsComment>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<TopicsComment> list = topicsCommentDao.getTopicsCommentsByTopicId(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
			for (TopicsComment comment : list) {// 设置时间用于页面显示
				comment.setShowcreatetime(DateUtils.formatDate(comment.getCreatetime(), "yyyy-MM-dd"));
			}
		}
		return page;
	}
	

}
