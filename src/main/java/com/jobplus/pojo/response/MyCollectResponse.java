package com.jobplus.pojo.response;

import java.util.List;

import com.jobplus.pojo.Docs;
import com.jobplus.pojo.MyCollect;

public class MyCollectResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2410616576881594273L;
	
	private List<MyCollect> list;
	
	public List<MyCollect> getList() {
		return list;
	}


	public void setList(List<MyCollect> list) {
		this.list = list;
	}


	public List<Docs> getDocsList() {
		return docsList;
	}


	public void setDocsList(List<Docs> docsList) {
		this.docsList = docsList;
	}


	private List<Docs> docsList;

}
