package com.jobplus.pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.List;

public class Courses extends PageParent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8695514269464389379L;

	private Integer id;

	private String coursesname;

	private String coursesurl;

	private String coursesimg;

	private Integer userid;

	private String coursesclass;

	private String coursestype;

	private Integer isvalid;

	private java.sql.Timestamp updatetime;

	private java.sql.Timestamp createtime;

	private Integer collectsum;

	private Integer recommendsum;

	private Integer likesum;

	private Integer readsum;

	private String recommend;

	private String intro;
	
	/**
	 * 课程目录
	 */
	private String contents;

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
     * 课程推荐(评论)列表
     */
    private Page<CoursesShare> commentList;
    
    /**
     * 收藏此课程的人
     */
    private List<User> collectUsers;
    
    /**
     * 相关课程
     */
    private List<Courses> relatedList;
    
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


	private CommonsMultipartFile coursesimgFile;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getObjCreator() {
		return objCreator;
	}

	public void setObjCreator(User objCreator) {
		this.objCreator = objCreator;
	}

	public Page<CoursesShare> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<CoursesShare> commentList) {
		this.commentList = commentList;
	}

	public List<Courses> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<Courses> relatedList) {
		this.relatedList = relatedList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getCollectUsers() {
		return collectUsers;
	}

	public void setCollectUsers(List<User> collectUsers) {
		this.collectUsers = collectUsers;
	}


	public String getCoursesname() {
		return coursesname;
	}

	public void setCoursesname(String coursesname) {
		this.coursesname = coursesname == null ? null : coursesname.trim();
	}

	public String getUserShareTime() {
		return userShareTime;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setUserShareTime(String userShareTime) {
		this.userShareTime = userShareTime;
	}

	public String getCoursesurl() {
		return coursesurl;
	}

	public void setCoursesurl(String coursesurl) {
		this.coursesurl = coursesurl == null ? null : coursesurl.trim();
	}

	public MyCollect getMyCollect() {
		return myCollect;
	}

	public void setMyCollect(MyCollect myCollect) {
		this.myCollect = myCollect;
	}

	public String getCoursesimg() {
		return coursesimg;
	}

	public void setCoursesimg(String coursesimg) {
		this.coursesimg = coursesimg == null ? null : coursesimg.trim();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCoursesclass() {
		return coursesclass;
	}

	public void setCoursesclass(String coursesclass) {
		this.coursesclass = coursesclass == null ? null : coursesclass.trim();
	}

	public String getCoursestype() {
		return coursestype;
	}

	public void setCoursestype(String coursestype) {
		this.coursestype = coursestype == null ? null : coursestype.trim();
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

	public CommonsMultipartFile getCoursesimgFile() {
		return coursesimgFile;
	}

	public void setCoursesimgFile(CommonsMultipartFile coursesimgFile) {
		this.coursesimgFile = coursesimgFile;
	}
}