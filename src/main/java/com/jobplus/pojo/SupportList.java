package com.jobplus.pojo;

import java.io.Serializable;

public class SupportList implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1160441667708758135L;

	private Integer id;

    private Integer userid;

    private Integer articleid;

    private Integer supportvalue;

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

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getSupportvalue() {
        return supportvalue;
    }

    public void setSupportvalue(Integer supportvalue) {
        this.supportvalue = supportvalue;
    }
}