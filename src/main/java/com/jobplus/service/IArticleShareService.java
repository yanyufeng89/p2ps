package com.jobplus.service;

import com.jobplus.pojo.ArticleShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface IArticleShareService {	
	
	ArticleShare insertAndReturnRecord(ArticleShare record,String contextPath,User user) ;
    /**
     * 获取评论列表
     * @param record
     * @return
     */
    Page<ArticleShare> getList(ArticleShare record);
    /**
     * @param record
     * @return
     */
    int delComment(ArticleShare record);
	
	
	int deleteByPrimaryKey(Integer id);

    int insert(ArticleShare record);

    int insertSelective(ArticleShare record);

    ArticleShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleShare record);

    int updateByPrimaryKey(ArticleShare record);
}
