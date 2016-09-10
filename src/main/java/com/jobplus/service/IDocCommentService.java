package com.jobplus.service;

import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface IDocCommentService {

	int deleteByPrimaryKey(Integer id);

    int insert(DocComment record);

    int insertSelective(DocComment record);

    DocComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DocComment record);

    int updateByPrimaryKeyWithBLOBs(DocComment record);
    
	/**
	 * 获取评论列表
	 * 
	 * @param record
	 * @return
	 */
	Page<DocComment> getList(DocComment record);
	
	DocComment insertAndReturnRecord(DocComment record,String contextPath,User user);
	

	/**
	 * 删除文档评论  对应评论数-1
	 * @param record
	 * @return
	 */
	int delComment(DocComment record);
}
