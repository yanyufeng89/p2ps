package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.ArticleShare;

public interface ArticleShareMapper {
	
	 /**
     * 获取评论列表
     * @param record
     * @return
     */
    List<ArticleShare> getList(ArticleShare record);
	
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleShare record);

    int insertSelective(ArticleShare record);

    ArticleShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleShare record);

    int updateByPrimaryKey(ArticleShare record);
}