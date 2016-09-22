package com.jobplus.pojo;

import java.io.Serializable;

public class Sms extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7241815258635745027L;

	private Integer id;

    private Integer senderid;

    private Integer receivedid;

    private java.sql.Timestamp sendtime;

    /**
     * 是否已阅     0未阅读   1已阅读
     */
    private Integer islook;

    /**消息类型   0系统消息    1私信  2关注通知(成为你的粉丝)                  2
	*11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
	*21书籍推荐新增回复   22书籍点赞										9
	*31课程新增推荐  32课程新增评论 	 33课程点赞							12
	*41文章新增推荐   42文章新增评论 	 43文章点赞							15
	*51站点新增推荐   52站点新增评论 	 53站点点赞							18
	*61文档新增推荐   62文档新增评论	 63文档点赞							21
	*3关注了你发布的话题  
    * */
    private Integer smstype;
    

	private String smstitle;
	 //关联主体ID
    private Integer relationid;

    private String smscontent;
    
    /**
     * 发送或者接受的时间   用于前端显示
     */
    private String senOrReTime;
    //用户名
    private String sendUserName;
    //关联的主体名称
    private String objectName;
    //关联主体ID
//    private Integer objectId;
    
    /**消息类型   0系统消息    1私信  2关注通知(成为你的粉丝)                  2
	*11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
	*21书籍推荐新增回复   22书籍点赞										9
	*31课程新增推荐  32课程新增评论 	 33课程点赞							12
	*41文章新增推荐   42文章新增评论 	 43文章点赞							15
	*51站点新增推荐   52站点新增评论 	 53站点点赞							18
	*61文档新增推荐   62文档新增评论	 63文档点赞							21
	*3关注了你发布的话题  
	*80邀请回答
    * */
    private Integer []SMSTYPES= {0,1,2,11,12,13,14,15,21,22,31,32,33,41,42,43,51,52,53,61,62,63,3,80};
    
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
//    private String []TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",
//    		"tbl_docs_comment tt right join tbl_docs t on tt.docId = t.ID",
//    		"tbl_books_share tt right join tbl_books t on tt.commId = t.ID",
//    		"tbl_books_isLiked tt right join tbl_books t on tt.bookID = t.ID",
//    		"tbl_topics_comment tt right join tbl_topics t on tt.topicsID = t.ID",
//    		"tbl_topics_isLiked tt right join tbl_topics t on tt.commId = t.ID",
//    		"tbl_courses_share tt right join tbl_courses t on tt.coursesID = t.ID",
//    		"tbl_courses_sLiked tt right join tbl_courses t on tt.coursesId = t.ID",
//    		"tbl_article_share tt right join tbl_article t on tt.articleID = t.id",
//    		"tbl_article_isLiked tt right join tbl_article t on tt.articleID = t.id",
//    		"tbl_sites_share tt right join tbl_sites t on tt.siteID = t.id",
//    "tbl_sites_isliked tt right join tbl_sites t on tt.commId = t.id"};
    
    public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

//	public Integer getObjectId() {
//		return objectId;
//	}
//
//	public void setObjectId(Integer objectId) {
//		this.objectId = objectId;
//	}

	/**
	 * {"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",5
			"tbl_docs_comment",
			"tbl_books_share",
			"tbl_books_isLiked",
			"tbl_topics_comment",
			"tbl_topics_isLiked",10
			"tbl_courses_share",
			"tbl_courses_sLiked",
			"tbl_article_share",
			"tbl_article_isLiked",
			"tbl_sites_share",15
    		"tbl_sites_isliked","tbl_user","tbl_docs_isLiked","tbl_topics"};
	 */
	public String[] getTABLENAMES() {
		return TABLENAMES;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getSenOrReTime() {
		return senOrReTime;
	}

	public void setSenOrReTime(String senOrReTime) {
		this.senOrReTime = senOrReTime;
	}

	/**
	 * /*消息类型   0系统消息    1私信  2关注通知(成为你的粉丝)                  2
11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
21书籍推荐新增回复   22书籍点赞										9
31课程新增推荐  32课程新增评论 	 33课程点赞							12
41文章新增推荐   42文章新增评论 	 43文章点赞							15
51站点新增推荐   52站点新增评论 	 53站点点赞							18
61文档新增推荐   62文档新增评论	 63文档点赞							21
	3关注话题	
	80邀请回答
	 */
	public Integer[] getSMSTYPES() {
		return SMSTYPES;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderid() {
        return senderid;
    }

    public void setSenderid(Integer senderid) {
        this.senderid = senderid;
    }

    public Integer getReceivedid() {
        return receivedid;
    }

    public void setReceivedid(Integer receivedid) {
        this.receivedid = receivedid;
    }

    public java.sql.Timestamp getSendtime() {
		return sendtime;
	}

	public void setSendtime(java.sql.Timestamp sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getIslook() {
        return islook;
    }

    public void setIslook(Integer islook) {
        this.islook = islook;
    }

    public Integer getSmstype() {
        return smstype;
    }

    public void setSmstype(Integer smstype) {
        this.smstype = smstype;
    }

    public String getSmstitle() {
        return smstitle;
    }

    public void setSmstitle(String smstitle) {
        this.smstitle = smstitle == null ? null : smstitle.trim();
    }

    public Integer getRelationid() {
        return relationid;
    }

    public void setRelationid(Integer relationid) {
        this.relationid = relationid;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent == null ? null : smscontent.trim();
    }
}