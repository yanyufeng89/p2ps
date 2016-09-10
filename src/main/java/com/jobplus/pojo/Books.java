package com.jobplus.pojo;

import java.io.Serializable;
import java.util.List;

public class Books extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6792343652409283467L;

	private Integer id;

    private String bookname;

    private String author;

    private String press;

    private String bookimg;

    private String bookclass;

    private String booktype;

    private Integer isvalid;

    private Integer collectsum;

    private Integer recommendsum;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

    private String intro;
    
    
    /**
     * 用户分享时间  用于前端页面显示
     */
    private String userShareTime;
    //
    private BookShare bookShare;
    //
    private MyCollect myCollect;
    /**
     * 书籍推荐(评论)列表
     */
    private Page<BookShare> commentList ;
    
    /**
     * 收藏此书的人
     */
    private List<User> collectUsers;
    
    /**
     * 相关书籍
     */
    private List<Books> relatedList ;
    
    /**
   	 * 主体创建人信息   headIcon、username等
   	 */
   	private User objCreator;
    /**
     * 收藏此书 ids
     */
    private String collectIds;    
    
    public User getObjCreator() {
		return objCreator;
	}

	public void setObjCreator(User objCreator) {
		this.objCreator = objCreator;
	}

	public String getCollectIds() {
		return collectIds;
	}

	public void setCollectIds(String collectIds) {
		this.collectIds = collectIds;
	}

	public BookShare getBookShare() {
		return bookShare;
	}

	public Page<BookShare> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<BookShare> commentList) {
		this.commentList = commentList;
	}

	public List<Books> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<Books> relatedList) {
		this.relatedList = relatedList;
	}

	public void setBookShare(BookShare bookShare) {
		this.bookShare = bookShare;
	}


	public String getUserShareTime() {
		return userShareTime;
	}


	public MyCollect getMyCollect() {
		return myCollect;
	}


	public void setMyCollect(MyCollect myCollect) {
		this.myCollect = myCollect;
	}


	public void setUserShareTime(String userShareTime) {
		this.userShareTime = userShareTime;
	}


	public Integer getId() {
        return id;
    }


	public List<User> getCollectUsers() {
		return collectUsers;
	}



	public void setCollectUsers(List<User> collectUsers) {
		this.collectUsers = collectUsers;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getBookimg() {
        return bookimg;
    }

    public void setBookimg(String bookimg) {
        this.bookimg = bookimg == null ? null : bookimg.trim();
    }

    public String getBookclass() {
        return bookclass;
    }

    public void setBookclass(String bookclass) {
        this.bookclass = bookclass == null ? null : bookclass.trim();
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype == null ? null : booktype.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getCollectsum() {
        return collectsum;
    }

    public void setCollectsum(Integer collectsum) {
        this.collectsum = collectsum;
    }

    public Integer getRecommendsum() {
        return recommendsum;
    }

    public void setRecommendsum(Integer recommendsum) {
        this.recommendsum = recommendsum;
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


	public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}