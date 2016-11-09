package com.jobplus.pojo;

import java.io.Serializable;
import java.util.List;

public class Article extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4372915208193652430L;

	private Integer id;

    private String title;

    private String articleurl;

    private String articleimg;

    private Integer userid;

    private String articleclass;

    private String articletype;

    private Integer isvalid;

    private java.sql.Timestamp updatetime;

    private java.sql.Timestamp createtime;

    private Integer collectsum;

    private Integer recommendsum;

    private Integer likesum;

    private Integer readsum;

    private String recommend;

    private String intro;
    
    private Integer  supportValue;//打赏值	supportValue	int(8)	8	
    
    private Integer  supportCount;//打赏次数	supportCount	int(8)	8
    
	
	private Integer ispublic;//是否匿名  1：匿名  其他非匿名	
    
    private MyCollect myCollect;
	/**
	 * 用户分享时间 用于前端页面显示
	 */
	private String userShareTime;
	 /**
     * 用于批量删除时的条件  (id组)
     */
    private String condition;    
    
    /**
     * 文章推荐(评论)列表
     */
    private Page<ArticleShare> commentList;
    
    /**
     * 收藏此文章的人
     */
    private List<User> collectUsers;
    /**
     * 相关文章
     */
    private List<Article> relatedList;

    /**
	 * 主体创建人信息   headIcon、username等
	 */
	private User objCreator;

	/**
	 * 点赞的人 ids
	 */
	private String likedIds;
	/**
	 * 收藏的人  ids 
	 */
	private String collectIds;
	
	/**
	 * 打赏的人 ids
	 */
	private String rewardIds;
	/**
	 * 打赏的人  list
	 */
	private List<User> rewardUsers;  
	
	/**
	 * 打赏发送打赏留言的载体
	 */
	private String smsContent;
	
	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public Integer getIspublic() {
		return ispublic;
	}

	public void setIspublic(Integer ispublic) {
		this.ispublic = ispublic;
	}

	public List<User> getRewardUsers() {
		return rewardUsers;
	}


	public void setRewardUsers(List<User> rewardUsers) {
		this.rewardUsers = rewardUsers;
	}

	public String getRewardIds() {
		return rewardIds;
	}

	public void setRewardIds(String rewardIds) {
		this.rewardIds = rewardIds;
	}

	public Integer getSupportValue() {
		return supportValue;
	}

	public void setSupportValue(Integer supportValue) {
		this.supportValue = supportValue;
	}

	public Integer getSupportCount() {
		return supportCount;
	}

	public void setSupportCount(Integer supportCount) {
		this.supportCount = supportCount;
	}

	public Page<ArticleShare> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<ArticleShare> commentList) {
		this.commentList = commentList;
	}

	public List<Article> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<Article> relatedList) {
		this.relatedList = relatedList;
	}

	public User getObjCreator() {
		return objCreator;
	}

	public void setObjCreator(User objCreator) {
		this.objCreator = objCreator;
	}

	public String getLikedIds() {
		return likedIds;
	}

	public void setLikedIds(String likedIds) {
		this.likedIds = likedIds;
	}

	public String getCollectIds() {
		return collectIds;
	}

	public void setCollectIds(String collectIds) {
		this.collectIds = collectIds;
	}

	public MyCollect getMyCollect() {
		return myCollect;
	}

	public void setMyCollect(MyCollect myCollect) {
		this.myCollect = myCollect;
	}

	public String getUserShareTime() {
		return userShareTime;
	}

	public void setUserShareTime(String userShareTime) {
		this.userShareTime = userShareTime;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<User> getCollectUsers() {
		return collectUsers;
	}

	public void setCollectUsers(List<User> collectUsers) {
		this.collectUsers = collectUsers;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl == null ? null : articleurl.trim();
    }

    public String getArticleimg() {
        return articleimg;
    }

    public void setArticleimg(String articleimg) {
        this.articleimg = articleimg == null ? null : articleimg.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getArticleclass() {
        return articleclass;
    }

    public void setArticleclass(String articleclass) {
        this.articleclass = articleclass == null ? null : articleclass.trim();
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype == null ? null : articletype.trim();
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
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

    public Integer getLikesum() {
        return likesum;
    }

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }

    public Integer getReadsum() {
        return readsum;
    }

    public void setReadsum(Integer readsum) {
        this.readsum = readsum;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}