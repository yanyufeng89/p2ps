package com.jobplus.pojo;

import java.io.Serializable;

public class BookShare extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1089657945152829662L;

	private Integer id;

    private Integer bookid;

    private Integer userid;

    private String recommend;

    private String knsite;

    private String extendeds;

    private Integer isdelete;

    private Integer commentby;
    
    private Integer likesum;

    private String sharetype;

    private String shareclass;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;
    
    /**
     * 评论人名
     */
    private String userName;
    /**
     * 被评论人名
     */
    private String commentbyName;
    /**
     * 评论人头像
     */
    private String userHeadIcon;
    /**
     * 被评论人头像
     */
    private String commentbyHeadIcon;
    
    /**
     * 用户分享时间  用于前端页面显示
     */
    private String userShareTime;
    
    /**
     * 点过赞的用户id
     */
    private String likedIds;
    
    /**
     * 用于批量删除时的条件  (id组)
     */
    private String condition;
    /**
     * 用户类型
     */
    private String usertype;
    

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
    private Books book;    

	public String getLikedIds() {
		return likedIds;
	}

	public void setLikedIds(String likedIds) {
		this.likedIds = likedIds;
	}

	public Books getBook() {
		return book;
	}

	public String getUserShareTime() {
		return userShareTime;
	}

	public void setUserShareTime(String userShareTime) {
		this.userShareTime = userShareTime;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentbyName() {
		return commentbyName;
	}

	public void setCommentbyName(String commentbyName) {
		this.commentbyName = commentbyName;
	}

	public String getUserHeadIcon() {
		return userHeadIcon;
	}

	public void setUserHeadIcon(String userHeadIcon) {
		this.userHeadIcon = userHeadIcon;
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

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getUserid() {
        return userid;
    }

    public Integer getLikesum() {
		return likesum;
	}

	public void setLikesum(Integer likesum) {
		this.likesum = likesum;
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

    public String getSharetype() {
        return sharetype;
    }

    public void setSharetype(String sharetype) {
        this.sharetype = sharetype == null ? null : sharetype.trim();
    }

    public String getShareclass() {
        return shareclass;
    }

    public void setShareclass(String shareclass) {
        this.shareclass = shareclass == null ? null : shareclass.trim();
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

}