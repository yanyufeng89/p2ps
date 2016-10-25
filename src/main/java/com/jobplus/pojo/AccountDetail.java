package com.jobplus.pojo;

import java.io.Serializable;

public class AccountDetail extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6878747939704734980L;

	private Integer id;

    private Integer accountid;

    private java.sql.Timestamp createtime;
    
    private String changeitem;

    private Integer changevalue;

    private String changecause;

    /**
     * 收入还是支出
     */
    private String changetype;
    /**
     * 更改类型  枚举  1收入  2支出 
     */
    private String[] CHANGETYPES = {"1","2"};
    /**
     * 更改项   枚举
     */
    private  String[] CHANGEITEMS = {"jiaMoney","points"};
    
    /**
     * 更改原因
     */
    private String[] CHANGECAUSES = {"分享文档","分享话题","分享书籍","分享课程","分享文章","分享站点","下载文档","回答问题","文档被下载","打赏文章","文章被打赏"};
    
    /**
     * 创建时间  	用于前端显示
     */
    private String showCreateTime;
    
    /**
     * 枚举积分值[]CHANGEVALUE = {1,2,3,4,5,6,7,8,9,10,15,20};
     */
    private Integer []CHANGEVALUES = {1,2,3,4,5,6,7,8,9,10,15,20};

    
    /**
     * 枚举积分值[]CHANGEVALUE = {1,2,3,4,5,6,7,8,9,10,15,20};
     */
	public Integer[] getCHANGEVALUES() {
		return CHANGEVALUES;
	}

	/**
	 * CHANGETYPES = 更改类型  枚举  1收入  2支出
	 */
	public String[] getCHANGETYPES() {
		return CHANGETYPES;
	}

	/**
	 * CHANGEITEMS = {"jiaMoney","points"};
	 */
	public String[] getCHANGEITEMS() {
		return CHANGEITEMS;
	}

	/**
	 *  CHANGECAUSES = {"分享文档","分享话题","分享书籍","分享课程","分享文章","分享站点","下载文档","回答问题"};
	 */
	public String[] getCHANGECAUSES() {
		return CHANGECAUSES;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 创建时间  	用于前端显示
     */
    public String getShowCreateTime() {
		return showCreateTime;
	}
    /**
     * 创建时间  	用于前端显示
     */
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}

	public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }


    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getChangeitem() {
        return changeitem;
    }

    public void setChangeitem(String changeitem) {
        this.changeitem = changeitem == null ? null : changeitem.trim();
    }

    public Integer getChangevalue() {
        return changevalue;
    }

    public void setChangevalue(Integer changevalue) {
        this.changevalue = changevalue;
    }

    public String getChangecause() {
        return changecause;
    }

    public void setChangecause(String changecause) {
        this.changecause = changecause == null ? null : changecause.trim();
    }

    public String getChangetype() {
        return changetype;
    }

    public void setChangetype(String changetype) {
        this.changetype = changetype == null ? null : changetype.trim();
    }
}