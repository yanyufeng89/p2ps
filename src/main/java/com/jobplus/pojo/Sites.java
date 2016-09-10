package com.jobplus.pojo;

import java.io.Serializable;
import java.util.List;

public class Sites extends PageParent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1628474718611805743L;

	private Integer id;

	private String title;

	private String siteurl;

	private String siteimg;

	private String siteclass;

	private String sitetype;

	private Integer isvalid;

	private java.sql.Timestamp updatetime;

	private java.sql.Timestamp createtime;

	private Integer collectsum;

	private Integer recommendsum;

	private Integer likesum;

	private Integer readsum;

	private Integer userid;

	private String recommend;

	private String intro;
	private MyCollect myCollect;
	/**
	 * 用户分享时间 用于前端页面显示
	 */
	private String userShareTime;
	/**
	 * 用于批量删除时的条件 (id组)
	 */
	private String condition;

	/**
	 * 课程推荐(评论)列表
	 */
	private Page<SiteShare> commentList;

	/**
	 * 收藏此课程的人
	 */
	private List<User> collectUsers;

	/**
	 * 相关课程
	 */
	private List<Sites> relatedList;
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
	
	public Page<SiteShare> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<SiteShare> commentList) {
		this.commentList = commentList;
	}

	public List<Sites> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<Sites> relatedList) {
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

	public String getSiteurl() {
		return siteurl;
	}

	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl == null ? null : siteurl.trim();
	}

	public String getSiteimg() {
		return siteimg;
	}

	public void setSiteimg(String siteimg) {
		this.siteimg = siteimg == null ? null : siteimg.trim();
	}

	public String getSiteclass() {
		return siteclass;
	}

	public void setSiteclass(String siteclass) {
		this.siteclass = siteclass == null ? null : siteclass.trim();
	}

	public String getSitetype() {
		return sitetype;
	}

	public void setSitetype(String sitetype) {
		this.sitetype = sitetype == null ? null : sitetype.trim();
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}
}