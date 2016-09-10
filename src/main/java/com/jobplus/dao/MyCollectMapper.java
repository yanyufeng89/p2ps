package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.MyCollect;

public interface MyCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyCollect record);

    int insertSelective(MyCollect record);

    MyCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyCollect record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(MyCollect record);
    
    
    /**
     * 个人中心-我的文档列表
     * @param record
     * @return
     */
    List<MyCollect> getMyDocsList(MyCollect record);
    
    /**
     * 个人中心-我的话题列表
     * @param record
     * @return
     */
    List<MyCollect> getMyTopicsList(MyCollect record);
    
    /**
     * 批量逻辑删除我的收藏和下载
     * @param condition
     * @return
     */
    int deleteMycollects(@Param(value="condition")String[] condition);
    /**
	 * 外部调用 删除收藏记录   必传字段  userid collecttype objectid actionType
	 * @param record
	 * @return
	 */
    int delMycollects(MyCollect record);
    
}