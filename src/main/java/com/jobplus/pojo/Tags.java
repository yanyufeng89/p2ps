package com.jobplus.pojo;

import java.io.Serializable;

public class Tags implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7173425189916296751L;

	private Integer id;

    private String tagname;

    private Integer parentid;

    private Integer rootid;

    private String tagtype;

    private Integer tier;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

    private Integer creator;

    private Integer isvalid;
    
    /**
     * 使用数量    用于统计
     */
    private Integer usesum;

	private String parentTagname;//父节点的名字  临时变量  add by jerry
	
    public Integer getUsesum() {
		return usesum;
	}

	public void setUsesum(Integer usesum) {
		this.usesum = usesum;
	}


    public String getParentTagname() {
		return parentTagname;
	}

	public void setParentTagname(String parentTagname) {
		this.parentTagname = parentTagname;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public String getTagtype() {
        return tagtype;
    }

    public void setTagtype(String tagtype) {
        this.tagtype = tagtype == null ? null : tagtype.trim();
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public java.sql.Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.sql.Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
    
    
}