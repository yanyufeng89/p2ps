package com.jobplus.pojo;

import java.io.Serializable;

public class CpnewsIslikedKey extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4118250695158680068L;

	private Integer userid;

    private Integer newsid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }
}