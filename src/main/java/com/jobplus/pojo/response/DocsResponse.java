package com.jobplus.pojo.response;

import java.util.List;

import com.jobplus.pojo.Docs;

public class DocsResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2269757939010891378L;
	private List<Docs> list;	

	public List<Docs> getList() {
		return list;
	}

	public void setList(List<Docs> list) {
		this.list = list;
	}
}
