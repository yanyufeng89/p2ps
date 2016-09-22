package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Attention;

public interface AttentionMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByAttentionInfo(Attention record);

    int insert(Attention record);
    
    /**
     *	无记录时插入; 有记录时不执行任何操作  
     *
     *	返回0   表示同一个不能关注多次
     * @param record
     * @return
     */
    int insertOrUpdate(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attention record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Attention record);
    
    /**
     * 获取我关注的话题  list
     * @param userId
     * @return
     */
    List<Attention> getMyAttentionList(Attention attention);
    int getMyAttentionListCount(Attention attention);
    
    /**
     * 批量删除我的关注
     * @param condition
     * @return
     */
    int deleteAttentions(@Param(value="condition")String[] condition);
    //获取我关注的人的总数
    public int getAttenManSum(@Param(value="userid")Integer userid);
    //我的粉丝数
    public int getFansSum(@Param(value="userid")Integer userid);
    //获取我关注的人 ids
    public String getAttenMan(@Param(value="userid")Integer userid);
    //我的粉丝 ids
    public String getFans(@Param(value="userid")Integer userid);
    
    
}