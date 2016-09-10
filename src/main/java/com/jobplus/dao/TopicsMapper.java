package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Topics;

public interface TopicsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Topics record);

    int insertSelective(Topics record);

    Topics selectByPrimaryKey(@Param(value="id")Integer id);
    
    Topics selectRecord(@Param(value="id")Integer id);

    /**
     * 更新话题的关注数     value 正负值表示加减
     * @param value
     * @param id
     * @return
     */
    int updateFollowsSum(@Param(value="value")Integer value,@Param(value="id")Integer id);
    
    
    int updateByPrimaryKeySelective(Topics record);

    int updateByPrimaryKeyWithBLOBs(Topics record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Topics record);
    
    
    /**
     * 我发布的话题
     * @param userID
     * @return
     */
    List<Topics> getMyTopicsUploaded(Topics record);
//    List<Topics> getMyTopicsUploaded(@Param(value="userID")String userID);
    /**
     * 批量逻辑删除话题
     * @param condition
     * @return
     */
    int deleteTopics(@Param(value="condition")String[] condition);
    
//    /**
//	 * 获取话题详情  包括评论
//	 * @param record
//	 * @return
//	 */
//	public Topics getTopicsDetail(Topics record);

    /**
     * 热门话题
     *
     * @param record
     * @return
     */
    List<Topics> getHotTopics(Topics record);

    /**
     * 最新话题
     *
     * @param record
     * @return
     */
    List<Topics> getLatestTopics(Topics record);

    /**
     * 待回答话题
     *
     * @param record
     * @return
     */
    List<Topics> getWaitReplyTopics(Topics record);

    /**
     * 精彩问答
     *
     * @param record
     * @return
     */
    List<Topics> getHotReplyTopics(Topics record);
}