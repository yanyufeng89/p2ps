package com.jobplus.service;

import com.jobplus.pojo.Books;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface IBooksService {

	int deleteByPrimaryKey(Integer id);

	int insert(Books record);

	int insertSelective(Books record);

	Books selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Books record);

	int updateByPrimaryKeyWithBLOBs(Books record);

	// 暂时注释 此方法为全量更新
	// int updateByPrimaryKey(Books record);

	/**
	 * 
	 * @param record
	 * @return
	 */
	Books getBookDetail(Books record);

	/**
	 * 书籍收藏、取消收藏 对象ID objectId(即BookId) actionType 1关注，0取消关注
	 * 
	 * @param record
	 * @return
	 */
	MyCollect collectBook(MyCollect record);

	/**
	 * 个人中心 -- 获取我分享的书籍列表
	 * 
	 * @param record
	 * @return
	 */
	Page<Books> getSharedBookList(Books record);

	/**
	 * 个人中心 -- 获取我收藏的书籍列表
	 * 
	 * @param record
	 * @return
	 */
	Page<Books> getCollectedBookList(Books record);

}
