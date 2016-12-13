package com.jobplus.pojo;

import java.io.Serializable;

public class DocComment extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5212499576555521056L;

	private Integer id;

    private Integer docid;

    private Integer rootid;

    private Integer parentid;

    private Integer tier;

    private Integer userid;

    private Integer commentby;

    private java.sql.Timestamp createtime;

    private Integer isdelete;

    private java.sql.Timestamp updatetime;

    private String comments;
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
     * 用户类型
     */
    private String usertype;
    

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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


    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public java.sql.Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.sql.Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}