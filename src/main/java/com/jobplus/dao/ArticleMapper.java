package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Article;

public interface ArticleMapper {
	
	 /**
     * 我分享的文章列表
     * @param record
     * @return
     */
    public List<Article> getSharedArticleList(Article record); 
    int getSharedArticleListCount(Article record); 
    
    /**
     * 我收藏的文章列表
     * @param record
     * @return
     */
    public List<Article> getCollectedArticleList(Article record); 
    int getCollectedArticleListCount(Article record); 
    
    /**
     * 批量删除我分享的文章
     * @param condition
     * @return
     */
    public int delSharedArticle(@Param(value="condition")String[] condition);
    
    /**
     * 更新阅读数  ++
     * @param id
     * @return
     */
    int updateReadSum(Integer id);
    
    Article selectByRecord(Article record);
    
	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);
}