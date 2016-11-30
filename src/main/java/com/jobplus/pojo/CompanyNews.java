package com.jobplus.pojo;

import java.io.Serializable;
import java.util.Date;

public class CompanyNews extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1697944607451296947L;

	private Integer id;

    private Integer compid;

    private String imgurl;

    private Integer commentsum;

    private Integer likesum;

    private Integer isvalid;

    private Integer istop;

    private Date toptime;

    private Date createtime;

    private Date updatetime;

    private String news;

    /**
	 * 点赞的人 ids
	 */
	private String likedIds;
    
    
    public String getLikedIds() {
		return likedIds;
	}

	public void setLikedIds(String likedIds) {
		this.likedIds = likedIds;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompid() {
        return compid;
    }

    public void setCompid(Integer compid) {
        this.compid = compid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getCommentsum() {
        return commentsum;
    }

    public void setCommentsum(Integer commentsum) {
        this.commentsum = commentsum;
    }

    public Integer getLikesum() {
        return likesum;
    }

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    public Date getToptime() {
        return toptime;
    }

    public void setToptime(Date toptime) {
        this.toptime = toptime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news == null ? null : news.trim();
    }
}