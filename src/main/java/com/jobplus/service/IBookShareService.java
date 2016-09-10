package com.jobplus.service;

import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface IBookShareService {

	int deleteByPrimaryKey(Integer id);
	
	/**
	 * 在书籍详情页下方 删除评论或者推荐语
	 * @param record
	 * @return
	 */
	int deleteByRecord(BookShare record);
	
	/**
	 * 批量删除书籍分享
	 * @param record
	 * @return
	 */
	int deleteByConditions(String conditions[],String bookIds[]);

	/**
	 * * 分享书籍
	 * 新增书籍推荐语：1.个人书籍分享数+1; 2.书籍评论数+1
	 * @param record
	 * @return
	 */
	int insert(BookShare record);
	
	/**
	 * 新增一条书籍推荐 or 评论 ，返回一个record.id
	 * @param record
	 * @return
	 */
	BookShare insertAndReturnId(BookShare record,String contextPath,User user);

	int insertSelective(BookShare record);

	BookShare selectByPrimaryKey(Integer id);
	
	/**
	 * 获取书籍分享详情用以编辑  
	 * @param id
	 * @return
	 */
	BookShare getBookShareDetailForEdit(Integer id);

	int updateByPrimaryKeySelective(BookShare record);

    /**
     * 分页显示书籍评论列表
     * @param record
     * @return
     */
    Page<BookShare> getList(BookShare record);
	// 暂时注释 此方法为全量更新
	// int updateByPrimaryKey(BookShare record);
}
