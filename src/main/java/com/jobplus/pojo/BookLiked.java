package com.jobplus.pojo;

import java.io.Serializable;

public class BookLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4919069770852338847L;

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