package com.jobplus.pojo;

import java.io.Serializable;

public class SiteShare extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1783146531303400501L;

	private Integer id;

    private Integer siteid;

    private Integer userid;

    private String knsite;

    private String extendeds;

    private String recommend;

    private Integer isdelete;

    private Integer commentby;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

    private Integer likesum;
    
    /**
     * 当前登录人id   判断评论列表是否有点赞记录
     */
    private Integer currentUserId;
    
    /**
     *  用于显示用户评论时间
     */
    private String userCommentTime;
    
    //用于显示评论左上角评论人被评论人名字和头像
    private String userName;
    private String userHeadIcon;
    private String commentbyName;
    private String commentbyHeadIcon;
    /**
     * 是否点赞      用于控制页面端是否点赞
     */
//    private Integer isLiked;
    

    public Integer getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getUserCommentTime() {
		return userCommentTime;
	}

	public void setUserCommentTime(String userCommentTime) {
		this.userCommentTime = userCommentTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadIcon() {
		return userHeadIcon;
	}

	public void setUserHeadIcon(String userHeadIcon) {
		this.userHeadIcon = userHeadIcon;
	}

	public String getCommentbyName() {
		return commentbyName;
	}

	public void setCommentbyName(String commentbyName) {
		this.commentbyName = commentbyName;
	}

	public String getCommentbyHeadIcon() {
		return commentbyHeadIcon;
	}

	public void setCommentbyHeadIcon(String commentbyHeadIcon) {
		this.commentbyHeadIcon = commentbyHeadIcon;
	}

//	public Integer getIsLiked() {
//		return isLiked;
//	}
//
//	public void setIsLiked(Integer isLiked) {
//		this.isLiked = isLiked;
//	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteid() {
        return siteid;
    }

    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getKnsite() {
        return knsite;
    }

    public void setKnsite(String knsite) {
        this.knsite = knsite == null ? null : knsite.trim();
    }

    public String getExtendeds() {
        return extendeds;
    }

    public void setExtendeds(String extendeds) {
        this.extendeds = extendeds == null ? null : extendeds.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getCommentby() {
        return commentby;
    }

    public void setCommentby(Integer commentby) {
        this.commentby = commentby;
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

	public Integer getLikesum() {
        return likesum;
    }

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }
}