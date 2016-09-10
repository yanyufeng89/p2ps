package com.jobplus.service;

import com.jobplus.pojo.Page;
import com.jobplus.pojo.TopicsComment;
import com.jobplus.pojo.User;

public interface ITopicsCommentService {

	int deleteByPrimaryKey(Integer id);

	int insertSelective(TopicsComment record);

	TopicsComment selectByPrimaryKey(Integer id);

	int updateByPrimaryKeyWithBLOBs(TopicsComment record);

	//暂时注释  此方法为全亮更新
//	int updateByPrimaryKey(TopicsComment record);

	int updateByPrimaryKeySelective(TopicsComment record);
	 /**
     * 我回复过的话题
     * @param record
     * @return
     */
    Page<TopicsComment> getMyTopicsComments(TopicsComment record);
    /**
	 * 个人中心--批量删除我的回复
	 * @param condition
	 * @return
	 */
	public int deleteTopicsComments(String[] condition,String userid);
	
	
	/**
	 * 获取话题的评论 或者  话题下回答的评论
	 * @param topicsComment
	 * @return
	 */
	public Page<TopicsComment> getPartTopicsComments(TopicsComment topicsComment);
	
	/**
	 * 根据排序要求获取话题下的回答列表
	 * @param record
	 * @return
	 */
	public Page<TopicsComment> getSortTopicsCommentsByTopicId(TopicsComment record);
	
	public TopicsComment insert(TopicsComment record,String contextPath,User user);
	
	/**
     * 点赞  取消 点赞 
     * @param record
     * @return
     */
	public int updateLikeSum(TopicsComment record);
	
	/**
	 * 在话题详情页删除 话题评论、话题回复、回答的评论
	 * @param topicsComment
	 * @return
	 */
	public int delCommentOnTopDetail(TopicsComment topicsComment);
}
