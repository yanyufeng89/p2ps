package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.AttentionMapper;
import com.jobplus.pojo.Attention;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IAttentionService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.ITopicsService;
import com.jobplus.utils.DateUtils;

@Service("attentionService")
public class AttentionServiceImpl implements IAttentionService {

	@Resource
	private AttentionMapper attentionDao;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ITopicsService topicsService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private ISmsService smsService;

	/**
	 * 获取我关注的话题 list
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public Page<Attention> getMyAttentionList(Attention record) {
		Page<Attention> page = new Page<Attention>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count =  attentionDao.getMyAttentionListCount(record);
		if(count < 1)
			return page;
		List<Attention> list =  attentionDao.getMyAttentionList(record);
		if (list.size() > 0) {
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
			for (Attention att : list) {// 设置时间用于页面显示
				att.setShowAttentiontime(DateUtils.formatDate(att.getAttentiontime(), "yyyy-MM-dd"));
			}
		}
		return page;
	}

	/**
	 * 个人中心--批量取消关注     （删除话题关注）
	 * @param condition
	 * @return
	 */
	@Transactional
	@Override
	public int deleteAttentions(String[] condition, String userid) {
		int ret = 0;
		ret = attentionDao.deleteAttentions(condition);
		if(ret> 0){
			// 对应用户操作我的关注 减少 st*******
			operationSumService.updOperationSum(5, 1, condition.length,null);
		}
		return ret;
	}

	/**
	 * 关注取消关注        对象类型 objectType 0:用户  1：话题,对象ID Objectid,关注fcl作  actionType 1关注，0取消关注
	 */	
	@Transactional
	@Override
	public int addFollows(Attention record, String contextPath, User user) {
		int ret = 0;
		int attentionID = seqService.getSeqByTableName("tbl_attention");
		int userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		record.setId(attentionID);
		record.setUserid(userid);
		// record.setObjectid(Integer.parseInt(Objectid));
		record.setColltype("0".equals(record.getObjectType()) ? "tbl_user" : "tbl_topics");

		if ("1".equals(record.getActionType())) {
			// 开始关注 插入记录
			ret = attentionService.addAttentions(record);
			if (ret > 0) {
				// 更新关注统计信息 。。。
				if ("0".equals(record.getObjectType())) {
					// 关注某人
					// 1 自己的关注总数加1
					operationSumService.updOperationSum(14, 0, 1, null);
					// 2 被关注人的粉丝总数加1
					operationSumService.updOperationSum(15, 0, 1, record.getObjectid().toString());

					// 添加消息通知
					smsService.addNotice(user, contextPath, new Sms().getTABLENAMES()[17], record.getObjectid(),
							record.getObjectid(), 2, record.getRelationidPg(),
							record.getObjectNamePg(),"");

				}
				if ("1".equals(record.getObjectType())) {
					// 关注话题
					// 1 自己的关注的话题总数加1
					operationSumService.updOperationSum(5, 0, 1, null);
					// 2 被关注话题的关注总数加1
					topicsService.updateFollowsSum(1, record.getObjectid());

					// 添加消息通知
					smsService.addNotice(user, contextPath, new Sms().getTABLENAMES()[2], record.getObjectid(),
							record.getObjCreatepersonPg(), 2, record.getRelationidPg(),
							record.getObjectNamePg(),"");
				}
			} else {
				return ret;
			}

		} else {
			// 取消关注
			ret = attentionService.deleteAttention(record);
			if (ret > 0) {
				// 更新关注统计信息 。。。
				if ("0".equals(record.getObjectType())) {
					// 取消关注某人
					// 1 自己的关注总数-1
					operationSumService.updOperationSum(14, 1, 1, null);
					// 2 被关注人的粉丝总数-1
					operationSumService.updOperationSum(15, 1, 1, record.getObjectid().toString());
				}
				if ("1".equals(record.getObjectType())) {
					// 取消关注话题
					// 1 自己的关注的话题总数-1
					operationSumService.updOperationSum(5, 1, 1, null);
					// 2 被关注话题的关注总数-1
					topicsService.updateFollowsSum(-1, record.getObjectid());
				}
			} else {
				return ret;
			}

		}
		return ret;
	}
	// 获取我关注的人的总数
	@Override
	public int getAttenManSum(Integer userid) {
		
		return attentionDao.getAttenManSum(userid);
	}
	@Override
	public int getFansSum(Integer userid) {
		
		return attentionDao.getFansSum(userid);
	}

	@Override
	public int addAttentions(Attention attention) {
		
		return attentionDao.insertOrUpdate(attention);
	}

	@Override
	public int deleteAttention(Attention record) {
		
		return attentionDao.deleteByAttentionInfo(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return attentionDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Attention record) {
		
		return attentionDao.insert(record);
	}

	@Override
	public int insertSelective(Attention record) {
		
		return attentionDao.insertSelective(record);
	}

	@Override
	public Attention selectByPrimaryKey(Integer id) {
		
		return attentionDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Attention record) {
		
		return attentionDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public String getAttenMan(Integer userid) {
		
		return attentionDao.getAttenMan(userid);
	}

	@Override
	public String getFans(Integer userid) {
		
		return attentionDao.getFans(userid);
	}

	

}
