package com.jobplus.pojo;

import java.io.Serializable;

/**
 * 我的收藏和下载
 * @param <T>
 *
 */
/**
 * @author Jerry 2016年6月29日下午4:29:34
 *
 */
public class MyCollect extends PageParent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2116251816446938895L;

	private Integer id;

	private Integer userid;

	/**
	 * 类型 暂时用表名存储
	 */
	private String collecttype;

	private Integer objectid;

	private Integer folderid;

	private java.sql.Timestamp colltime;

	private Integer actionType;// 动作类型
	/**
	 * //动作类型枚举 0下载 1收藏 ACTIONTYPE ={0,1}
	 */
	private Integer[] ACTIONTYPES = { 0, 1 };
	/**
	 * 类型 暂时用表名存储 tbl_docs tbl_topics tbl_books
	 * COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books"};
	 */
	private String[] COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article",
			"tbl_sites" };

	/**
	 * 用于判断动作：取消收藏还是收藏
	 */
	private int judgeTodo;

	/**
	 * 用于页面显示时间
	 */
	private String showcolltime;
	/**
	 * 批量删除时的传入条件
	 */
	private String condition;

	/**
	 * 用于页面端显示文档 和话题
	 */
	private Docs docs;

	private Topics topics;

	public Topics getTopics() {
		return topics;
	}

	public int getJudgeTodo() {
		return judgeTodo;
	}

	public void setJudgeTodo(int judgeTodo) {
		this.judgeTodo = judgeTodo;
	}

	public String getShowcolltime() {
		return showcolltime;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setShowcolltime(String showcolltime) {
		this.showcolltime = showcolltime;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public Docs getDocs() {
		return docs;
	}

	public void setDocs(Docs docs) {
		this.docs = docs;
	}

	public Integer[] getACTIONTYPES() {
		return ACTIONTYPES;
	}

	public void setACTIONTYPES(Integer[] aCTIONTYPES) {
		ACTIONTYPES = aCTIONTYPES;
	}

	/**
	 * { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
	 */
	public String[] getCOLLECTTYPES() {
		return COLLECTTYPES;
	}

	public void setCOLLECTTYPES(String[] cOLLECTTYPES) {
		COLLECTTYPES = cOLLECTTYPES;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCollecttype() {
		return collecttype;
	}

	public void setCollecttype(String collecttype) {
		this.collecttype = collecttype;
	}

	public Integer getObjectid() {
		return objectid;
	}

	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	public Integer getFolderid() {
		return folderid;
	}

	public void setFolderid(Integer folderid) {
		this.folderid = folderid;
	}

	public java.sql.Timestamp getColltime() {
		return colltime;
	}

	public void setColltime(java.sql.Timestamp colltime) {
		this.colltime = colltime;
	}

}