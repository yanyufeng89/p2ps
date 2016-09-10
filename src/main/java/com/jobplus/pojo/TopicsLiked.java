package com.jobplus.pojo;

import java.io.Serializable;

public class TopicsLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7293274150963130887L;

	private Integer userid;

    private Integer commid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }
}