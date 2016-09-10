package com.jobplus.pojo;

import java.io.Serializable;

public class Resource implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6387059058731686810L;

	private Integer id;

    private String resourcename;

    private Integer parentresid;

    private Integer resid;

    private Integer isvalid;

    private java.sql.Timestamp createtime;

    private String varemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename == null ? null : resourcename.trim();
    }

    public Integer getParentresid() {
        return parentresid;
    }

    public void setParentresid(Integer parentresid) {
        this.parentresid = parentresid;
    }

    public Integer getResid() {
        return resid;
    }

    public void setResid(Integer resid) {
        this.resid = resid;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getVaremark() {
        return varemark;
    }

    public void setVaremark(String varemark) {
        this.varemark = varemark == null ? null : varemark.trim();
    }
}