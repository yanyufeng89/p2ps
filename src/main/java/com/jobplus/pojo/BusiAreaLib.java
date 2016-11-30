package com.jobplus.pojo;

import java.io.Serializable;
import java.util.Date;

public class BusiAreaLib implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7815113653748846059L;

	private Integer id;

    private String busiareaname;

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

    public String getBusiareaname() {
        return busiareaname;
    }

    public void setBusiareaname(String busiareaname) {
        this.busiareaname = busiareaname == null ? null : busiareaname.trim();
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