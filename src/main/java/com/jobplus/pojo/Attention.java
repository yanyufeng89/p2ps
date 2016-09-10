package com.jobplus.pojo;

import java.io.Serializable;

public class Attention extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6271139271953018318L;
	
	private Integer id;
	
    private Integer userid;

    private Integer objectid;

    private String colltype;

    private Integer isnotify;

    private java.sql.Timestamp attentiontime;
    
    
    /**
     * colltype 枚举
     */
    private String[] COLLTYPES = {"tbl_user","tbl_topics"};
    
    /**
     * 用于查出数据页面端显示
     */
    private String showAttentiontime;

    private Topics topics;
    
    /**
     * 对象类型 objectType 0:用户  1：话题
     */
    private String objectType;
    
    
    /**
     * actionType 1关注，0取消关注
     */
    private String actionType;
    /**
     * 判读是否存在
     */
    private int isExit;
    
    
    public int getIsExit() {
		return isExit;
	}


	public void setIsExit(int isExit) {
		this.isExit = isExit;
	}


	public Topics getTopics() {
		return topics;
	}


	public void setTopics(Topics topics) {
		this.topics = topics;
	}


	public String[] getCOLLTYPES() {
		return COLLTYPES;
	}

	public Integer getId() {
        return id;
    }

    public String getShowAttentiontime() {
		return showAttentiontime;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public void setShowAttentiontime(String showAttentiontime) {
		this.showAttentiontime = showAttentiontime;
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

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getColltype() {
        return colltype;
    }

    public void setColltype(String colltype) {
        this.colltype = colltype;
    }

    public Integer getIsnotify() {
        return isnotify;
    }

    public void setIsnotify(Integer isnotify) {
        this.isnotify = isnotify;
    }

	public java.sql.Timestamp getAttentiontime() {
		return attentiontime;
	}

	public void setAttentiontime(java.sql.Timestamp attentiontime) {
		this.attentiontime = attentiontime;
	}

}