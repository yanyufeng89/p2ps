package com.jobplus.pojo;

import java.io.Serializable;

public class TypeConfig implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 582753755199391545L;

	private Integer typeid;

    private Integer rootid;

    private Integer parentid;

    private String typename;

    private String typegroup;

    private Integer isvalid;

    private java.sql.Timestamp createtime;

    private java.sql.Timestamp lastupdatetime;

    private Integer tier;
    
    /**
     * 父节点名称  表里无此字段
     */
    private String parentTypeName;

    public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getTypegroup() {
        return typegroup;
    }

    public void setTypegroup(String typegroup) {
        this.typegroup = typegroup == null ? null : typegroup.trim();
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

	public java.sql.Timestamp getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(java.sql.Timestamp lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}