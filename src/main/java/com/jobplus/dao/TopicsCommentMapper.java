package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.TopicsComment;

public interface TopicsCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicsComment record);

    int insertSelective(TopicsComment record);

    TopicsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopicsComment record);

    int updateByPrimaryKeyWithBLOBs(TopicsComment record);

    
    /**
     * 点赞  取消 点赞 
     * @param record
     * @return
     */
    int updateLikeSum(TopicsComment record);
    
    /**
     * 我回复过的话题
     * @param record
     * @return
     */
    List<TopicsComment> getMyTopicsComments(TopicsComment record);
    int getMyTopicsCommentsCount(TopicsComment record);
    /**
	 * 个人中心--批量删除我的回复
	 * 
	 * @param condition
	 * @return
	 */
    int deleteTopicsComments(@Param(value="condition") String[] condition);
    
    /**
     * 获取某条话题的所有回答   关联用户表的用户名和用户头像     （用于话题详情页的展示）
     * @param TopicId
     * @return
     */
    List<TopicsComment> getTopicsCommentsByTopicId(TopicsComment record);//topicsid  sortType
    int getTopicsCommentsByTopicIdCount(TopicsComment record);//topicsid  sortType
//    List<TopicsComment> getTopicsCommentsByTopicId(@Param(value="TopicId")Integer TopicId,@Param(value="sortType")Integer sortType);
    
    /**
	 * 获取话题的评论 或者  话题下回答的评论
	 * @param topicsComment
	 * @return
	 */
	public List<TopicsComment> getPartTopicsComments(TopicsComment topicsComment);
	int getPartTopicsCommentsCount(TopicsComment topicsComment);
    
    
}