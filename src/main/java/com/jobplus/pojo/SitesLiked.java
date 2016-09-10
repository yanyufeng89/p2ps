package com.jobplus.pojo;

import java.io.Serializable;

public class SitesLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5178964141535837098L;

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