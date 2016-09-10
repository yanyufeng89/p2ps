package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.CoursesShare;

public interface CoursesShareMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CoursesShare record);

	int insertSelective(CoursesShare record);

	CoursesShare selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CoursesShare record);

	int updateByPrimaryKey(CoursesShare record);

	/**
	 * 获取评论列表
	 * 
	 * @param record
	 * @return
	 */
	List<CoursesShare> getList(CoursesShare record);
}