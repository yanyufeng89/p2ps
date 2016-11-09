package com.jobplus.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Jerry
 * 2016年11月2日上午10:19:37
 *
 */
public class ConstantMsg {

	/**
	 * 消息类型 0系统消息 1私信 2关注通知(成为你的粉丝) 2 11话题新增回答 12话题的回答新增评论 13话题新增评论 14话题评论新增回复
	 * 15话题回答点赞 7 21书籍推荐新增回复 22书籍点赞 9 31课程新增推荐 32课程新增评论 33课程点赞 12 41文章新增推荐
	 * 42文章新增评论 43文章点赞 15 51站点新增推荐 52站点新增评论 53站点点赞 18 61文档新增推荐 62文档新增评论 63文档点赞
	 * 21 3关注了你发布的话题
	 */

	/**
	 * 获取提示信息 eg 关注了您;赞了你分享的文档;打赏了您的文
	 */
	private static final Map<String, String> map = new HashMap<String, String>();
	/**
	 * 根据表名获取拼接Url eg："tbl_topics","/topics/getTopicsDetail/"
	 * "tbl_topics_comment","/topics/getTopicsDetail/"
	 */
	public static final Map<String, String> urlMap = new HashMap<String, String>();
	static {
		map.put("2", "关注了您");
		map.put("11", "回答了你分享的话题");
		map.put("12", "回复了你话题的评论");
		map.put("13", "评论了你的话题");
		map.put("14", "回复了你的话题");
		map.put("15", "赞了你的话题");
		map.put("21", "回复了你分享的书籍");
		map.put("22", "赞了你分享的书籍");
		map.put("31", "推荐了你分享的课程");
		map.put("32", "评论了你分享的课程");
		map.put("33", "赞了你分享的课程");
		map.put("41", "评论了你分享的文章");
		map.put("42", "评论了你分享的文章");
		map.put("43", "赞了你分享的文章");
		map.put("51", "评论了你分享的站点");
		map.put("52", "评论了你分享的站点");
		map.put("53", "赞了你分享的站点");
		map.put("61", "评论了你分享的文档");
		map.put("62", "评论了你分享的文档");
		map.put("63", "赞了你分享的文档");
		map.put("3", "关注了你发布的话题");
		map.put("80", "邀请您回答");
		map.put("90", "下载了您的文档");
		map.put("100", "打赏了您的文章");

		urlMap.put("tbl_user", "");
		urlMap.put("tbl_topics", "/topics/getTopicsDetail/");
		urlMap.put("tbl_topics_comment", "/topics/getTopicsDetail/");
		urlMap.put("tbl_topics_isLiked", "/topics/getTopicsDetail/");
		urlMap.put("tbl_courses", "/courses/getCourseDetail/");
		urlMap.put("tbl_courses_share", "/courses/getCourseDetail/");
		urlMap.put("tbl_courses_sLiked", "/courses/getCourseDetail/");
		urlMap.put("tbl_sites", "/sites/getSiteDetail/");
		urlMap.put("tbl_sites_share", "/sites/getSiteDetail/");
		urlMap.put("tbl_sites_isliked", "/sites/getSiteDetail/");
		urlMap.put("tbl_article", "/article/getArticleDetail/");
		urlMap.put("tbl_article_share", "/article/getArticleDetail/");
		urlMap.put("tbl_article_isLiked", "/article/getArticleDetail/");
		urlMap.put("tbl_docs", "/docs/getDocsDetail/");
		urlMap.put("tbl_docs_comment", "/docs/getDocsDetail/");
		urlMap.put("tbl_books", "/books/getBookDetail/");
		urlMap.put("tbl_books_share", "/books/getBookDetail/");
		urlMap.put("tbl_books_isLiked", "/books/getBookDetail/");
		urlMap.put("tbl_docs_isLiked", "/docs/getDocsDetail/");

	}
	
	private Integer []SMSTYPES= {0,1,2,11,12,13,
			14,15,21,22,31,
			32,33,41,42,43,
			51,52,53,61,62,
			63,3,80,90,100};

	private String []TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",
		"tbl_docs_comment",
		"tbl_books_share",
		"tbl_books_isLiked",
		"tbl_topics_comment",
		"tbl_topics_isLiked",
		"tbl_courses_share",
		"tbl_courses_Liked",
		"tbl_article_share",
		"tbl_article_isLiked",
		"tbl_sites_share",
		"tbl_sites_isliked","tbl_user","tbl_docs_isLiked","tbl_topics"};

	public Integer[] getSMSTYPES() {
		return SMSTYPES;
	}

	public String[] getTABLENAMES() {
		return TABLENAMES;
	}
	
	
//	public String getObjUrl(String contextPath,String tableName,String objId,String ObjName){
//		//对象url
//		String objUrl = "<a target='_blank' href='"+contextPath+urlMap.get(tableName) +objId+ "'>" + ObjName + "</a>";
//		return objUrl;
//	}

}
