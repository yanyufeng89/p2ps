package com.jobplus.pojo;

import java.io.Serializable;

public class SysLoginLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6775773403240066160L;

	private Integer id;

    private Integer userid;

    private java.sql.Timestamp logintime;

    private String userip;

    private String serviceip;

    private Integer loginmode;

    private String sessionid;

    private String url;

    private String seed;

    private Integer stepsum;

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

    public java.sql.Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(java.sql.Timestamp logintime) {
		this.logintime = logintime;
	}

	public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip == null ? null : userip.trim();
    }

    public String getServiceip() {
        return serviceip;
    }

    public void setServiceip(String serviceip) {
        this.serviceip = serviceip == null ? null : serviceip.trim();
    }

    public Integer getLoginmode() {
        return loginmode;
    }

    public void setLoginmode(Integer loginmode) {
        this.loginmode = loginmode;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed == null ? null : seed.trim();
    }

    public Integer getStepsum() {
        return stepsum;
    }

    public void setStepsum(Integer stepsum) {
        this.stepsum = stepsum;
    }
}