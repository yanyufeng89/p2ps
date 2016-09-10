package com.jobplus.pojo;

import java.io.Serializable;

public class ArticleLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4635171115949292766L;

	private Integer userid;

    private Integer articleid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }
}