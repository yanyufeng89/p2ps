package com.jobplus.pojo;

import java.io.Serializable;
import java.util.Date;

public class SkillLibrary implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5513767868278163210L;

	private Integer id;

    private String skillname;

    private Integer parentid;

    private Integer rootid;

    private Date updatetime;

    private Date createtime;

    private Integer creator;

    private Integer isvalid;

    private Integer usesum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname == null ? null : skillname.trim();
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
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

    public Integer getUsesum() {
        return usesum;
    }

    public void setUsesum(Integer usesum) {
        this.usesum = usesum;
    }
}