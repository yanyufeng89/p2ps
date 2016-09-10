package com.jobplus.service;

import com.jobplus.pojo.Page;
import com.jobplus.pojo.Topics;
import com.jobplus.pojo.TopicsComment;

public interface ITopicsService {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加话题
	 * @param record
	 * @return
	 */
	Topics insert(Topics record);

	int insertSelective(Topics record);

	Topics selectByPrimaryKey(Integer id);

	/**
     * 更新话题的关注数     value 正负值表示加减
     * @param value
     * @param id
     * @return
     */
    int updateFollowsSum(Integer value,Integer id);
	
	int updateByPrimaryKeySelective(Topics record);

	int updateByPrimaryKeyWithBLOBs(Topics record);
	
	//暂时注释  此方法为全亮更新
//	int updateByPrimaryKey(Topics record);
	
	/**
	 * 我发布的话题
	 * @param record
	 * @return
	 */
	public Page<Topics> getMyTopicsUploaded(Topics record);
	/**
	 * 批量逻辑删除topics
	 * 
	 * @param condition
	 * @param userid
	 * @return
	 */
	public int deleteTopics(String[] condition,String userid);
	/**
	 * 获取某条话题的所有回答   关联用户表的用户名和用户头像     （用于话题详情页的展示） sortType排序方式
	 * @param record
	 * @return
	 */
	public Topics getTopicsDetail(TopicsComment record);

//	public List findFandIds(String topicsId);
//	/**
//	 * 关注话题
//	 * @param record
//	 * @return
//	 */
//	public int attentionTopics(Topics record);

	/**
	 * 话题列表
	 *
	 * @param theme
	 * @param record
	 * @return
	 */
	public Page<Topics> searchTopics(int theme, Topics record);

}
