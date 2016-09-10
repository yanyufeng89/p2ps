package com.jobplus.pojo;

import java.io.Serializable;

public class MobileSms implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -365582462016986957L;

	private Integer id;

    private String mobile;

    private String validatecode;

    private String ip;

    private java.sql.Timestamp createtime;

    private java.sql.Timestamp expiretime;

    private java.sql.Timestamp usingtime;

    private Integer isuse;

    private Integer sendstatus;

    private String sendresult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getValidatecode() {
        return validatecode;
    }

    public void setValidatecode(String validatecode) {
        this.validatecode = validatecode == null ? null : validatecode.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }


    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public java.sql.Timestamp getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(java.sql.Timestamp expiretime) {
		this.expiretime = expiretime;
	}

	public java.sql.Timestamp getUsingtime() {
		return usingtime;
	}

	public void setUsingtime(java.sql.Timestamp usingtime) {
		this.usingtime = usingtime;
	}

	public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public Integer getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(Integer sendstatus) {
        this.sendstatus = sendstatus;
    }

    public String getSendresult() {
        return sendresult;
    }

    public void setSendresult(String sendresult) {
        this.sendresult = sendresult == null ? null : sendresult.trim();
    }
}