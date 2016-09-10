package com.jobplus.pojo;

import java.io.Serializable;

public class VisitHistory extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7242684044151914680L;

	private Integer id;

    private Integer visitorid;

    private Integer userid;

    private java.sql.Timestamp visittime;

    private Integer visittype;
    
    private String visiturl;

    /**
     * 用户名
     */
    private String userName;    
    /**
     * 头像
     */
    private String headIcon;
    /**
     * 用于前端显示时间
     */
    private String showVTime;
    
    /**
     * 用户描述
     */
    private String description;
    
    /**
     * 访问类型  1：我的主页   2
     */
    private Integer []VISITTYPES = {1,2,3};    

    /**
     * 粉丝ids
     */
    private String fansIds;
    
    private Integer count;
    
	public String getFansIds() {
		return fansIds;
	}

	public void setFansIds(String fansIds) {
		this.fansIds = fansIds;
	}
	/**
     * 访问类型  1：我的主页   2
     */
	public Integer[] getVISITTYPES() {
		return VISITTYPES;
	}
	
	/**
	 * operationsum 的 关注总数
	 */
	private Integer attentionsum;
	/**
	 * operationsum 的 粉丝总数
	 */
	private Integer fanssum;

	public String getShowVTime() {
		return showVTime;
	}

	public void setShowVTime(String showVTime) {
		this.showVTime = showVTime;
	}

	public Integer getAttentionsum() {
		return attentionsum;
	}

	public void setAttentionsum(Integer attentionsum) {
		this.attentionsum = attentionsum;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFanssum() {
		return fanssum;
	}

	public void setFanssum(Integer fanssum) {
		this.fanssum = fanssum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(Integer visitorid) {
        this.visitorid = visitorid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public java.sql.Timestamp getVisittime() {
		return visittime;
	}

	public void setVisittime(java.sql.Timestamp visittime) {
		this.visittime = visittime;
	}

	public Integer getVisittype() {
        return visittype;
    }

    public void setVisittype(Integer visittype) {
        this.visittype = visittype;
    }

    public String getVisiturl() {
        return visiturl;
    }

    public void setVisiturl(String visiturl) {
        this.visiturl = visiturl == null ? null : visiturl.trim();
    }
}