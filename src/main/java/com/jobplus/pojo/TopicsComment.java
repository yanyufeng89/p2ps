package com.jobplus.pojo;

import java.io.Serializable;

public class TopicsComment extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3072723422958767423L;

	private Integer id;

    private Integer topicsid;

    private Integer rootcommid;

    private Integer parentcommid;

    private Integer tier;

    private Integer userid;

	private Integer commentby;

    private Integer isdelete;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

	private String commcontent;
	
	private Integer type;
	
	private Integer replysum;
	
	private Integer likesum;

	private Integer collectsum;
	
	private Integer isPublic;

	/**
     * 用于查出数据页面端显示
     */
    private String showcreatetime;
    
    /**
     *  用于据页面端显示用户名
     */
    private String tmpUserName;
    
    /**
     * 用于据页面端显示用户头像
     */
    private String tmpHeadIcon;    
    /**
     *  用于据页面端显示被回复人名
     */
    private String commentbyUserName;
    
    /**
     * 用于据页面端被回复人头像
     */
    private String commentbyHeadIcon;  
    
    /**
     * 点赞的人
     */
    private String likedIds;  
    

    /**
     * 用于排序  1：时间  2：回复总数  默认时间排序
     */
    private Integer  sortType;    
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLikedIds() {
		return likedIds;
	}

	public void setLikedIds(String likedIds) {
		this.likedIds = likedIds;
	}

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public Integer getReplysum() {
		return replysum;
	}
	public void setReplysum(Integer replysum) {
		this.replysum = replysum;
	}

	public Integer getLikesum() {
		return likesum;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public void setLikesum(Integer likesum) {
		this.likesum = likesum;
	}

	public Integer getCollectsum() {
		return collectsum;
	}

	public String getCommentbyUserName() {
		return commentbyUserName;
	}

	public void setCommentbyUserName(String commentbyUserName) {
		this.commentbyUserName = commentbyUserName;
	}

	public String getCommentbyHeadIcon() {
		return commentbyHeadIcon;
	}

	public void setCommentbyHeadIcon(String commentbyHeadIcon) {
		this.commentbyHeadIcon = commentbyHeadIcon;
	}

	public void setCollectsum(Integer collectsum) {
		this.collectsum = collectsum;
	}
    
	public String getTmpHeadIcon() {
		return tmpHeadIcon;
	}

	public void setTmpHeadIcon(String tmpHeadIcon) {
		this.tmpHeadIcon = tmpHeadIcon;
	}

	public String getTmpUserName() {
		return tmpUserName;
	}

	public void setTmpUserName(String tmpUserName) {
		this.tmpUserName = tmpUserName;
	}

	public String getShowcreatetime() {
		return showcreatetime;
	}

	public void setShowcreatetime(String showcreatetime) {
		this.showcreatetime = showcreatetime;
	}

	private Topics topics;


	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicsid() {
        return topicsid;
    }

    public void setTopicsid(Integer topicsid) {
        this.topicsid = topicsid;
    }

    public Integer getRootcommid() {
        return rootcommid;
    }

    public void setRootcommid(Integer rootcommid) {
        this.rootcommid = rootcommid;
    }

    public Integer getParentcommid() {
        return parentcommid;
    }

    public void setParentcommid(Integer parentcommid) {
        this.parentcommid = parentcommid;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCommentby() {
        return commentby;
    }

    public void setCommentby(Integer commentby) {
        this.commentby = commentby;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
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

	public String getCommcontent() {
        return commcontent;
    }

    public void setCommcontent(String commcontent) {
        this.commcontent = commcontent == null ? null : commcontent.trim();
    }
}