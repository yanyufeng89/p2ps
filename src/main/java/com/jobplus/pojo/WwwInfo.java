package com.jobplus.pojo;

/**
 * 网页信息类
 * 
 * @author Jerry 2016年7月19日下午7:37:27
 *
 */
public class WwwInfo {

	String url;
	String title;
	String intro;
	String imgUrl;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * this method get the imgUrl of value
	 * 
	 * @return Returns the imgUrl.
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * this method set the imgUrl of value
	 * 
	 * @param enName
	 *            The imgUrl to set.
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
