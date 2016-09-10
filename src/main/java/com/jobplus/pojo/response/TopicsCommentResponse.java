package com.jobplus.pojo.response;

import com.jobplus.pojo.TopicsComment;

public class TopicsCommentResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 708105758405598341L;
	
	private TopicsComment topicsComment;

	public TopicsComment getTopicsComment() {
		return topicsComment;
	}

	public void setTopicsComment(TopicsComment topicsComment) {
		this.topicsComment = topicsComment;
	}

}
