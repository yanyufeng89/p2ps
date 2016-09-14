package com.jobplus.pojo;

import java.util.HashMap;
import java.util.Map;

public class UpdTableColumn {

	private Integer objectId;
	
	private String tableName;
	/**
	 * 表的列名
	 */
	private String tableColumn;
	
	/**
	 * 是增加还是减少
	 */
	private String addOrDecrease;
	/**
	 * 增加 减少数量
	 */
	private Integer AdOrDeNum;
	/**
	 * 类对应的表
	 */
	public static String []TABLENAMES;
	
	private String[] ADDORDECREASE = { "+", "-" };
//	private String []OTHERTABLENAMES = {"tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
	
	
	
	private static String[] DOCSCOLUMNS ;
	private static String[] BOOKSCOLUMNS;
	private static String[] TOPICSCOLUMNS;	
	private static String[] COURSESCOLUMNS;
	private static String[] ARTICLESCOLUMNS;
	private static String[] SITESCOLUMNS;
	
	private static String[] DOCSCOMMENTCOLUMNS;
	private static String[] BOOKSHARECOLUMNS;
	private static String[] TOPICSCOMMENTCOLUMNS;
	private static String[] COURSESSHARECOLUMNS;
	private static String[] ARTICLESHARECOLUMNS;
	private static String[] SITESSHARECOLUMNS;
	
	public static Map<String,String[]> TABLECOLUMN = new HashMap<String,String[]>();

	static{
		TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",
				"tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
		
		DOCSCOLUMNS = new String[]{"collectSum","downSum","readSum","likeSum","recommendSum"};
		BOOKSCOLUMNS = new String[]{"collectSum","recommendSum" };
		TOPICSCOLUMNS = new String[]{"followsSum","readSum","replySum","likeSum","futilitySum","collectSum","commentSum"};	
		COURSESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
		ARTICLESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
		SITESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
		
		DOCSCOMMENTCOLUMNS = new String[]{ "likesum"};
		BOOKSHARECOLUMNS = new String[]{ "likesum"};
		TOPICSCOMMENTCOLUMNS = new String[]{ "replySum","likeSum","collectSum"};
		COURSESSHARECOLUMNS = new String[]{ "likesum"};
		ARTICLESHARECOLUMNS = new String[]{ "likesum"};
		SITESSHARECOLUMNS = new String[]{ "likesum"};
		
		
		TABLECOLUMN.put(TABLENAMES[0], DOCSCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[1], BOOKSCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[2], TOPICSCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[3], COURSESCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[4], ARTICLESCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[5], SITESCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[6], DOCSCOMMENTCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[7], BOOKSHARECOLUMNS);
		TABLECOLUMN.put(TABLENAMES[8], TOPICSCOMMENTCOLUMNS);
		TABLECOLUMN.put(TABLENAMES[9], COURSESSHARECOLUMNS);
		TABLECOLUMN.put(TABLENAMES[10], ARTICLESHARECOLUMNS);
		TABLECOLUMN.put(TABLENAMES[11], SITESSHARECOLUMNS);
		
	}

	
	public static Map<String, String[]> getTABLECOLUMN() {
		return TABLECOLUMN;
	}
	public String[] getADDORDECREASE() {
		return ADDORDECREASE;
	}	
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String[] getTABLENAMES() {
		return TABLENAMES;
	}

	private Docs doc;
	public String getTableColumn() {
		return tableColumn;
	}
	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}
	public String getAddOrDecrease() {
		return addOrDecrease;
	}
	public void setAddOrDecrease(String addOrDecrease) {
		this.addOrDecrease = addOrDecrease;
	}
	public Integer getAdOrDeNum() {
		return AdOrDeNum;
	}
	public void setAdOrDeNum(Integer adOrDeNum) {
		AdOrDeNum = adOrDeNum;
	}
	public Docs getDoc() {
		return doc;
	}
	public void setDoc(Docs doc) {
		this.doc = doc;
	}
	public Topics getTopic() {
		return topic;
	}
	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}

	private Topics topic;
	private Books book;
	
	public static void main(String[] args) {
		System.out.println(TABLECOLUMN.get(TABLENAMES[0])[0]);
	}

}
