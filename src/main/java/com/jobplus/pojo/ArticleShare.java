package com.jobplus.pojo;

import java.io.Serializable;

public class ArticleShare extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4144301403931739696L;

	private Integer id;

    private Integer articleid;

    private Integer userid;

    private String recommend;

    private String knsite;

    private String extendeds;

    private Integer isdelete;

    private Integer commentby;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

    private Integer likesum;
    
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
     * 点赞的人  ids
     */
//    private String likedIds;
    

//	public String getLikedIds() {
//		return likedIds;
//	}
//
//	public void setLikedIds(String likedIds) {
//		this.likedIds = likedIds;
//	}

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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
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