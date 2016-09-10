package com.jobplus.pojo;

import java.io.Serializable;

public class DocsLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6892215915068847768L;

	private Integer userid;

    private Integer docid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }
}