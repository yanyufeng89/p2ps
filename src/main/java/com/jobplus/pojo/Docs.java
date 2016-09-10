package com.jobplus.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Docs extends PageParent implements Serializable {
	private static final long serialVersionUID = 2850304562760087418L;
	private Integer id;

	private String title;

	private String description;

	private String filepath;

	private String docpages;

	private java.sql.Timestamp createtime;

	private java.sql.Timestamp lastedittime;

	private Integer userid;

	private Integer collectsum;

	private Integer downsum;

	private Integer readsum;

	private Integer likesum;
	
	private Integer recommendsum;

	private Integer downvalue;

	private String docsuffix;

	private String doctype;

	private String docclass;

	private Integer ispublic;	

	/**
	 * 文件预览路径 
	 */
	private String readurl;

	/**
	 * 0 私有 ;1 分享 ;2 草稿
	 */
	private Integer isvalid;
	
	private Integer isconverter;

	/**
	 * 新增MultipartFile
	 */
	private MultipartFile file;


	/**
	 * 用于前端页面显示
	 */
	private String showcreatetime;
	
//	/**
//	 * 当前用户是否点赞入参 
//	 */
//	private Integer cutUserIsliked;
	
	/**
	 * 文档推荐(评论)列表
	 */
	private Page<DocComment> commentList;

	/**
	 * 下载此文档的人
	 */
	private List<User> downloadUsers;

	/**
	 * 相关文档
	 */
	private List<Docs> relatedList;
	
//	private String userName;
//	
//	private String headIcon;
	
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
	

	public User getObjCreator() {
		return objCreator;
	}

	public void setObjCreator(User objCreator) {
		this.objCreator = objCreator;
	}


//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getHeadIcon() {
//		return headIcon;
//	}
//
//	public void setHeadIcon(String headIcon) {
//		this.headIcon = headIcon;
//	}

	public List<Docs> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<Docs> relatedList) {
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

	public Page<DocComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(Page<DocComment> commentList) {
		this.commentList = commentList;
	}

	public List<User> getDownloadUsers() {
		return downloadUsers;
	}

//	public Integer getCutUserIsliked() {
//		return cutUserIsliked;
//	}
//
//	public void setCutUserIsliked(Integer cutUserIsliked) {
//		this.cutUserIsliked = cutUserIsliked;
//	}

	public void setDownloadUsers(List<User> downloadUsers) {
		this.downloadUsers = downloadUsers;
	}

	public String getShowcreatetime() {
		return showcreatetime;
	}


	public Integer getRecommendsum() {
		return recommendsum;
	}

	public void setRecommendsum(Integer recommendsum) {
		this.recommendsum = recommendsum;
	}

	public void setShowcreatetime(String showcreatetime) {
		this.showcreatetime = showcreatetime;
	}

	public Integer getIsvalid() {
		return isvalid;
	}

	/**
	 * 默认设置为1
	 * @param isvalid
	 */
	public void setIsvalid(Integer isvalid) {
		if(isvalid == null){
			this.isvalid = 1;
		}else{
			this.isvalid = isvalid;
		}
		
	}

	public String getReadurl() {
		return readurl;
	}

	public void setReadurl(String readurl) {
		this.readurl = readurl;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Integer getIsconverter() {
		return isconverter;
	}

	public void setIsconverter(Integer isconverter) {
		this.isconverter = isconverter;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath == null ? null : filepath.trim();
	}

	public String getDocpages() {
		return docpages;
	}

	public void setDocpages(String docpages) {
		this.docpages = docpages == null ? null : docpages.trim();
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getCollectsum() {
		return collectsum;
	}

	public void setCollectsum(Integer collectsum) {
		this.collectsum = collectsum;
	}

	public Integer getDownsum() {
		return downsum;
	}

	public void setDownsum(Integer downsum) {
		this.downsum = downsum;
	}

	public Integer getReadsum() {
		return readsum;
	}

	public void setReadsum(Integer readsum) {
		this.readsum = readsum;
	}


	public Integer getLikesum() {
		return likesum;
	}

	public void setLikesum(Integer likesum) {
		this.likesum = likesum;
	}

	public Integer getDownvalue() {
		return downvalue;
	}

	public void setDownvalue(Integer downvalue) {
		this.downvalue = downvalue;
	}

	public String getDocsuffix() {
		return docsuffix;
	}

	public void setDocsuffix(String docsuffix) {
		this.docsuffix = docsuffix == null ? null : docsuffix.trim();
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype == null ? null : doctype.trim();
	}

	public String getDocclass() {
		return docclass;
	}

	public void setDocclass(String docclass) {
		this.docclass = docclass == null ? null : docclass.trim();
	}

	public Integer getIspublic() {
		return ispublic;
	}

	public void setIspublic(Integer ispublic) {
		this.ispublic = ispublic;
	}
}