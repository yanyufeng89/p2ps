package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.BusiAreaLib;
import com.jobplus.pojo.MyHomePage;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface ICompanyService {
	/**
	 * 获取最近分享的6类
	 * @param record
	 * @return
	 */
//	public Page<MyHomePage> getRecentShare(HttpServletRequest request,MyHomePage record);
	
	/**
	 * 获取我分享的某一类 tableName;tableColumn;userid;
	 * @param request
	 * @param record
	 * @return
	 */
//	public Page<MyHomePage> getOneShares(HttpServletRequest request,MyHomePage record);
	
	
	/**
	 *  个人主页  && 浏览他人主页
	 * @param mv
	 * @param cutUserid
	 * @return
	 */
	public ModelAndView getHomePage(HttpServletRequest request,ModelAndView mv, String userid,String cutUserid,String isReview);
	/**
	 * 实时获取用户分享的总数（成功的）
	 */
//	public OperationSum getRealSum(Integer userid);
	
	
	/**
	 * 上传图片到服务器返回图片url
	 * @return
	 */
	public String uploadImg(MultipartFile[] files,User user);

	/**
	 * 新增业务领域 标签
	 */
	int insertBusLib(BusiAreaLib record);
	/**
     * 统计企业用户完整度
     */
    int cpInfoCompletion(Integer id);
}
