package com.jobplus.pojo.response;

import java.util.List;

import com.jobplus.pojo.Tags;
import com.jobplus.pojo.Topics;
import com.jobplus.pojo.TopicsComment;

public class TopicsResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 980076065098376524L;
	
	private List<Topics> list;
	
	/**
	 * 标签list
	 */
	private List<Tags> tagsList;
	
	/**
	 * 话题评论（回答评论）
	 */
	private List<TopicsComment> topicsCommentList;	


	public List<TopicsComment> getTopicsCommentList() {
		return topicsCommentList;
	}

	public void setTopicsCommentList(List<TopicsComment> topicsCommentList) {
		this.topicsCommentList = topicsCommentList;
	}

	public List<Tags> getTagsList() {
		return tagsList;
	}

	public void setTagsList(List<Tags> tagsList) {
		this.tagsList = tagsList;
	}

	public List<Topics> getList() {
		return list;
	}

	public void setList(List<Topics> list) {
		this.list = list;
	}
}
