package com.jobplus.service;

import java.util.List;

import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface IMyCollectService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(MyCollect record);

    int insertSelective(MyCollect record);

    MyCollect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyCollect record);

	/**
     * 个人中心-我的文档列表  收藏或者下载
     * @param record
     * @return
     */
    Page<MyCollect> getMyDocsList(MyCollect record);
    
    /**
     * 个人中心-我的话题列表
     * @param record
     * @return
     */
    List<MyCollect> getMyTopicsList(MyCollect record);
    
	/**
	 * 批量逻辑删除我的收藏和下载
	 * downloadOrCollect 用于区别删除下载还是收藏 
	 * @param condition
	 * @param userid
	 * @param downloadOrCollect
	 * @return
	 */
	public int deleteMycollects(String[] condition,MyCollect record);
	
	/**
	 * 外部调用 删除收藏记录   必传字段  userid collecttype objectid actionType
	 * @param record
	 * @return
	 */
	public int delMycollects(MyCollect record);
    
}
