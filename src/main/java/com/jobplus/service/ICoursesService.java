package com.jobplus.service;

import com.jobplus.pojo.Courses;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;

public interface ICoursesService {

	int deleteByPrimaryKey(Integer id);

	int insert(Courses record);

	int insertSelective(Courses record);

	Courses selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Courses record);

	int updateByPrimaryKeyWithBLOBs(Courses record);

	int updateByPrimaryKey(Courses record);

	/**
	 * //同时 插入一条书籍分享记录
	 * @param record
	 * @return
	 */
	int insertCouseAndCourseShare(Courses record);
	
	/**
	 * 我分享的课程列表
	 * @param record
	 * @return
	 */
	Page<Courses> getSharedCourseList(Courses record);
	
	/**
	 * 我收藏的课程列表
	 * @param record
	 * @return
	 */
	Page<Courses> getCollectedCourseList(Courses record);
	
	/**
	 * 批量删除个人中心-我分享的课程
	 * @param conditions
	 * @return
	 */
	int delSharedCourses(String conditions[]);
	
	/**
	 * 获取课程详情 浏览数++
	 * @param record
	 * @return
	 */
	Courses getCourseDetail(Courses record);
	
	MyCollect collectCourse(MyCollect record);
	
}
