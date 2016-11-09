package com.jobplus.pojo;

import com.jobplus.utils.Common;
import org.apache.solr.common.SolrDocumentList;

import java.io.Serializable;
import java.util.List;

public class Topics extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8665473566069262455L;

	private Integer id;
	
    private String title;

    private java.sql.Timestamp createtime;

    private java.sql.Timestamp lastedittime;

    private Integer createperson;

    private Integer followssum;

    private Integer readsum;

    private Integer replysum;

    private Integer likesum;
    
    private Integer commentsum;

    private Integer futilitysum;

    private Integer collectsum;

    private String topicstype;

    private String topicsclass;

    private Integer ispublic;

    private String content;
    
    private Integer isvalid;
    
    //悬赏新增俩字段
    private Integer acceptStatus;//回答采纳状态  1:已采纳   0:不悬赏  -1：取消悬赏 2:等待悬赏
    private Integer rewardValue;//悬赏值
    
    
    /**
     * 图片
     */
    private String topicimg;

    /**
     * 简介
     */
    private String intro;

    /**
     * 话题评论类
     */
    private Page<TopicsComment> commentList; 
    
    /**
     * 当前话题的粉丝
     */
    private List<User> fansList;
    
	/**
	 * 相关话题列表    
	 */
    private List<SolrDocumentList> relatedList;  
    /**
     * 粉丝 ids
     */
    private String fansIds;

    
    /**
	 * 主体创建人信息   headIcon、username等
	 */
	private User objCreator;

    /**
	 * 用于前端页面显示
	 */
	private String showcreatetime;

    private String condition;
	
	
	public User getObjCreator() {
		return objCreator;
	}

	public Integer getAcceptStatus() {
		return acceptStatus;
	}

	public void setAcceptStatus(Integer acceptStatus) {
		this.acceptStatus = acceptStatus;
	}

	public Integer getRewardValue() {
		return rewardValue;
	}

	public void setRewardValue(Integer rewardValue) {
		this.rewardValue = rewardValue;
	}

	public void setObjCreator(User objCreator) {
		this.objCreator = objCreator;
	}

	public String getShowcreatetime() {
		return showcreatetime;
	}

	public void setShowcreatetime(String showcreatetime) {
		this.showcreatetime = showcreatetime;
	}

	public Page<TopicsComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<TopicsComment> commentList) {
		this.commentList = commentList;
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

    public Integer getCommentsum() {
		return commentsum;
	}

	public void setCommentsum(Integer commentsum) {
		this.commentsum = commentsum;
	}

    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public java.sql.Timestamp getLastedittime() {
		return lastedittime;
	}

	public void setLastedittime(java.sql.Timestamp lastedittime) {
		this.lastedittime = lastedittime;
	}

	public Integer getCreateperson() {
        return createperson;
    }

    public void setCreateperson(Integer createperson) {
        this.createperson = createperson;
    }

    public Integer getFollowssum() {
        return followssum;
    }

    public void setFollowssum(Integer followssum) {
        this.followssum = followssum;
    }

    public Integer getReadsum() {
        return readsum;
    }

    public void setReadsum(Integer readsum) {
        this.readsum = readsum;
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

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }

    public Integer getFutilitysum() {
        return futilitysum;
    }

    public void setFutilitysum(Integer futilitysum) {
        this.futilitysum = futilitysum;
    }

    public Integer getCollectsum() {
        return collectsum;
    }

    public void setCollectsum(Integer collectsum) {
        this.collectsum = collectsum;
    }

    public String getTopicstype() {
        return topicstype;
    }

    public void setTopicstype(String topicstype) {
        this.topicstype = topicstype == null ? null : topicstype.trim();
    }

    public String getTopicsclass() {
        return topicsclass;
    }

    public void setTopicsclass(String topicsclass) {
        this.topicsclass = topicsclass == null ? null : topicsclass.trim();
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
        this.topicimg = Common.getFirstImg(content);
        this.intro = Common.filterHtml(content);
    }

    public Integer getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Integer isvalid) {
			this.isvalid = isvalid;
		
	}

	public List<User> getFansList() {
		return fansList;
	}

	public void setFansList(List<User> fansList) {
		this.fansList = fansList;
	}


	public List<SolrDocumentList> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<SolrDocumentList> relatedList) {
		this.relatedList = relatedList;
	}

	public String getFansIds() {
		return fansIds;
	}

	public void setFansIds(String fansIds) {
		this.fansIds = fansIds;
	}

    public String getTopicimg() {
        return topicimg;
    }

    public String getIntro() {
        return intro;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}