package com.jobplus.service;

import com.jobplus.pojo.Article;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface IArticleService {

	/**
	 * 分享文章 同时插入一条分享
	 * 
	 * @param record
	 * @return
	 */
	int insertArticleAndArticlehare(Article record);
	/**
	 * 我分享的课程列表
	 * @param record
	 * @return
	 */
	Page<Article> getSharedArticleList(Article record);

	/**
	 * 我收藏的文章
	 * 
	 * @param record
	 * @return
	 */
	Page<Article> getCollectedArticleList(Article record);

	/**
	 * 批量删除个人中心-我分享的文章
	 * 
	 * @param conditions
	 * @return
	 */
	int delSharedArticle(String conditions[]);

	/**
	 * 获取文章详情 浏览数++
	 * 
	 * @param record
	 * @return
	 */
	Article getArticleDetail(Article record);

	/**
	 * 文章的收藏与取消
	 * 
	 * @param record
	 * @return
	 */
	MyCollect collectArticle(MyCollect record);

	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

}
