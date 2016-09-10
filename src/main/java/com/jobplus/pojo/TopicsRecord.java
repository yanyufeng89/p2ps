package com.jobplus.pojo;

import java.io.Serializable;

public class TopicsRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2562193195912403636L;

	private Integer id;

    private Integer topicsid;

    private Integer userid;

    private Integer actiontype;

    private java.sql.Timestamp actiontime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicsid() {
        return topicsid;
    }

    public void setTopicsid(Integer topicsid) {
        this.topicsid = topicsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getActiontype() {
        return actiontype;
    }

    public void setActiontype(Integer actiontype) {
        this.actiontype = actiontype;
    }

	public java.sql.Timestamp getActiontime() {
		return actiontime;
	}

	public void setActiontime(java.sql.Timestamp actiontime) {
		this.actiontime = actiontime;
	}

}