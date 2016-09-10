package com.jobplus.pojo;

import java.util.HashMap;
import java.util.Map;

public class MyHomePage extends PageParent {

	private Integer id;
	private Integer userid;
	private String title;
	private String type;
	private java.sql.Timestamp createtime;
	
	
	/**
	 * 分享时间 前端显示
	 */
	private String sharetime;
	/**
	 * 访问的URL
	 */
	private String objurl;
	/**
	 * 所查表名  ：doc topcis 等等
	 * tbl_docs  tbl_article   tbl_sites tbl_topics    title  
		tbl_books  bookname
		tbl_courses  coursesName
	 */
	private String tableName;
	/**
	 * 所查表的字段
	 */
	private String tableColumn;
	/**
	 * 所查表的字段 where后面字段    
	 * 只有  tbl_topics  是 createPerson
	 * 其余都是 userid
	 */
	private String tableColumn2;
	/**
	 * 
	 */
	private String objName;
	
	

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableColumn() {
		return tableColumn;
	}
	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	private static Map<String, String> urlMap = new HashMap<String,String>();
	static{
		urlMap.put("tbl_topics","/myCenter/getTopicsDetail?topicId=");
		urlMap.put("tbl_books","/books/getBookDetail?id=");		
		urlMap.put("tbl_courses","/courses/getCourseDetail?id=");
		urlMap.put("tbl_sites","/sites/getSiteDetail?id=");	
		urlMap.put("tbl_article","/article/getArticleDetail?id=");	
		urlMap.put("tbl_docs","/docs/getDocsDetail?id=");
	}
	
	

	public Integer getId() {
		return id;
	}
	/**
	 * 拼接Url
	 * urlMap.put("tbl_topics","/myCenter/getTopicsDetail?topicId=");
		urlMap.put("tbl_books","/books/getBookDetail?id=");		
		urlMap.put("tbl_courses","/courses/getCourseDetail?id=");
		urlMap.put("tbl_sites","/sites/getSiteDetail?id=");	
		urlMap.put("tbl_article","/article/getArticleDetail?id=");	
		urlMap.put("tbl_docs","/docs/getDocsDetail?id=");
	 */
	public static Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableColumn2() {
		return tableColumn2;
	}
	public void setTableColumn2(String tableColumn2) {
		this.tableColumn2 = tableColumn2;
	}
	public Integer getUserid() {
		return userid;
	}

	public String getObjurl() {
		return objurl;
	}
	public void setObjurl(String objurl) {
		this.objurl = objurl;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public String getSharetime() {
		return sharetime;
	}

	public void setSharetime(String sharetime) {
		this.sharetime = sharetime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}


}
