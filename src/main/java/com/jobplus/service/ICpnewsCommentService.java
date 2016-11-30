package com.jobplus.service;

import com.jobplus.pojo.CpnewsComment;
import com.jobplus.pojo.CpnewsIslikedKey;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface ICpnewsCommentService {

	/**
	 * 获取企业快讯评论
	 * @param record
	 * @return
	 */
	Page<CpnewsComment> getComtList(CpnewsComment record);
	
	/**
	 * 新增快讯评论
	 */
	CpnewsComment insertAndReturnRecord(CpnewsComment record,String contextPath,User user);
	
	/**
	 *删除快讯评论
	 */
	int delComment(CpnewsComment record);
	
	/**
	 * 快讯点赞
	 */
	int likeNews(CpnewsIslikedKey record,String contextPath,User user) ;
	
	/**
	 * 取消点赞
	 */
	int cancelLikeNews(CpnewsIslikedKey record);
	
}
