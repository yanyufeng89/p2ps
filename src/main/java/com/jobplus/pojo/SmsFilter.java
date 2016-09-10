package com.jobplus.pojo;

import java.io.Serializable;

public class SmsFilter implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1457707619585727330L;

	private Integer id;

    private Integer userid;

    private Integer filterlevel;

    private java.sql.Timestamp createtime;

    private Integer filteritem;
    
    /**
     * 用于判断是新增还是更新
     */
    private Integer count;

    /**
     * 过滤等级枚举  1允许所有;0只允许我关注的;-1不允许任何人
     */
    private Integer []FILTERLEVELS = {1,0,-1};
    /**
     * 过滤项 0消息;1私信
     */
    private Integer []FILTERITEMS = {0,1};
    
    
    /**
     * 粉丝Ids
     */
    private String fansIds;
    
    public String getFansIds() {
		return fansIds;
	}

	public void setFansIds(String fansIds) {
		this.fansIds = fansIds;
	}

	public Integer[] getFILTERLEVELS() {
		return FILTERLEVELS;
	}

	public Integer[] getFILTERITEMS() {
		return FILTERITEMS;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

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

    public Integer getFilterlevel() {
        return filterlevel;
    }

    public void setFilterlevel(Integer filterlevel) {
        this.filterlevel = filterlevel;
    }


    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getFilteritem() {
        return filteritem;
    }

    public void setFilteritem(Integer filteritem) {
        this.filteritem = filteritem;
    }
}