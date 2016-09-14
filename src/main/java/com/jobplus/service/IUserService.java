package com.jobplus.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobplus.pojo.GridQuery;
import org.springframework.web.multipart.MultipartFile;

import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface IUserService {

	User get(Integer userid);

	User post(User record);

	User put(User record, HttpServletRequest request);

	int delete(Integer userid);

	int checkAccount(String checkName, String checkValue);

	public List<?> findUserRoleByName(String userName);

	public User findUserByName(String userName);

	/**
	 * 通过用户Id获取用户简单信息 包括用户统计
	 * 
	 * @param userid
	 * @return
	 */
	public User getUserSimpleInformation(Integer userid);
	
	/**
	 * 获取所有粉丝
	 * @param collType
	 * @param objectId
	 * @return
	 */
	List<User> getFansListInformation(String collType,Integer objectId);
	
	/**
	 * 获取所有粉丝的ID
	 * @param topicsId
	 * @return
	 */
	public List<Map<String,Object>> findFandIds(String topicsId) ;
	
	
	/**
	 *  获取某本书籍的收藏者
	 * @param collectType   COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books"};
	 * @param actionType    收藏还是下载      （ 动作类型枚举   0下载 1收藏）ACTIONTYPE ={0,1}
	 * @param bookId    书籍ID
	 * @return
	 */
	public List<User> getCollectUsers(String collectType,Integer actionType,Integer bookId);
	
	 /**
     * 个人中心：我的粉丝列表
     * @param User record
     * @return
     */
    Page<User> getMyFansList(User record);    
    /**
     * 个人中心：我关注的人列表
     * @param User record
     * @return
     */
    Page<User> getAttenManList(User record);


    int updateByPrimaryKeySelective(User record);
    /**
     * @param record
     * @return
     */
    int updUserInfoIncHeadIcon(MultipartFile files,User record, HttpServletRequest request, HttpServletResponse response);
    /**
	 * 更新密码
	 *
	 * @param user
	 * @return
	 */
	int changePassword(User user);
    
    /**
     * 更新用户操作统计数到session中
     * @param request
     */
    public void getMyHeadTopAndOper(HttpServletRequest request);
    
    /**
     * 更新用消息统计数 到session中
     * @param request
     */
    public void getSmsOper(HttpServletRequest request);
    
    /**
     * 查询operationSum表数据 只是查询
     * @param request
     * @return
     */
    public OperationSum getOperationSum(HttpServletRequest request);

	/**
	 * 后台查询用户列表
	 *
	 * @param gridQuery
	 * @return
	 */
	Map<String, Object> getAllUsers(GridQuery gridQuery);

}
