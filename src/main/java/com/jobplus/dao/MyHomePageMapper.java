package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.MyHomePage;

public interface MyHomePageMapper {

	/**
	 * 获取最近分享的6类
	 * @param record
	 * @return
	 */
	public List<MyHomePage> getRecentShare(MyHomePage record);
	
	/**
	 * 获取某一类的分享
	 * @param record
	 * @return
	 */
	public List<MyHomePage> getOneShares(MyHomePage record);
	/**
	 * 只返回数据的分享    因为书籍信息需要关联查询
	 * @param record
	 * @return
	 */
	public List<MyHomePage> getOneSharesJustToBooks(MyHomePage record);
}
