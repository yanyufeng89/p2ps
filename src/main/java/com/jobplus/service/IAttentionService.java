package com.jobplus.service;

import com.jobplus.pojo.Attention;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

/**
 * 我的关注
 * @author Jerry
 * 2016年6月30日下午4:16:23
 *
 */
public interface IAttentionService {
	int deleteByPrimaryKey(Integer id);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attention record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Attention record);

	/**
     * 获取我关注的话题  Page
     * @param userId
     * @return
     */
	public Page<Attention> getMyAttentionList(Attention attention);
	
	/**
	 * 个人中心--批量取消关注     （删除话题关注）
	 * @param condition
	 * @return
	 */
	public int deleteAttentions(String[] condition,String userid);
	
	/**
	 * 个人中心--增加关注     
	 * @param condition
	 * @return
	 */
	public int addAttentions(Attention attention);
	
	/**
	 * 个人中心--取消关注     
	 * @param condition
	 * @return
	 */
	public int deleteAttention(Attention record);
	
	/**
	 * 关注取消关注        对象类型 objectType 0:用户  1：话题,对象ID objectId,关注fcl作  actionType 1关注，0取消关注
	 * @param objectType
	 * @param objectId
	 * @param actionType
	 * @return
	 */
	public int addFollows(Attention record,String contextPath,User user);
	
	/**
	 * 获取我关注的人的总数
	 * @param userid
	 * @return
	 */
	int getAttenManSum(Integer userid);
	//我的粉丝数
	int getFansSum(Integer userid);
	//我关注的人  ids
	String getAttenMan(Integer userid);
	//我的粉丝   ids
	String getFans(Integer userid);
	
}
