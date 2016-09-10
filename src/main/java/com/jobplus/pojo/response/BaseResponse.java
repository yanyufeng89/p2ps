package com.jobplus.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.User;
import com.jobplus.utils.ConstantManager;

/**
 * 响应基类
 * @author Jerry
 * 2016年6月22日下午1:57:40
 *
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1894858884967258804L;
	
	private String currentUserid;
	// 返回状态码
	private String returnStatus = ConstantManager.ERROR_STATUS;
	// 返回信息
	private String returnMsg;

	private List<?> baseResponseList;
	
	/**
	 * 用于返回统计数
	 */
	private OperationSum operationSum;
	
	
	/**
	 * 用户对象
	 */
	private User user;
	
	//泛型类
	private Object obj;
	
	private Object obj2;

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public OperationSum getOperationSum() {
		return operationSum;
	}

	public void setOperationSum(OperationSum operationSum) {
		this.operationSum = operationSum;
	}
	
	public List<?> getBaseResponseList() {
		return baseResponseList;
	}

	public void setBaseResponseList(List<?> baseResponseList) {
		this.baseResponseList = baseResponseList;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getCurrentUserid() {
		return currentUserid;
	}

	public void setCurrentUserid(String currentUserid) {
		this.currentUserid = currentUserid;
	}
}
