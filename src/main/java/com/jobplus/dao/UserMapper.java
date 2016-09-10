package com.jobplus.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int checkAccount(@Param("fieldName")String fieldName,@Param("fieldValue")String fieldValue);

    User getAccount(@Param("fieldName")String fieldName,@Param("fieldValue")String fieldValue);

    
    /**
     * 通过用户Id获取用户简单信息    包括用户统计   
     * @param userid
     * @return
     */
    User getUserSimpleInformation(@Param("userID")Integer userID);
    
    /**
     * 查找粉丝列表信息  
     * @param userid
     * @return
     */
    List<User> getFansListInformation(@Param("collType")String collType,@Param("objectId")Integer objectId);
    
    /**
     * 个人中心：我的粉丝列表
     * @param User record
     * @return
     */
    List<User> getMyFansList(User record);    
    /**
     * 个人中心：我关注的人列表
     * @param User record
     * @return
     */
    List<User> getAttenManList(User record);
    
}