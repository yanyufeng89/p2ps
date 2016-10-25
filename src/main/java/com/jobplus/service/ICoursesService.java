package com.jobplus.service;

import com.jobplus.pojo.Courses;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICoursesService {

	int deleteByPrimaryKey(Integer id);

	int insert(Courses record);

	int insertSelective(Courses record);

	Courses selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Courses record);

	int updateByAdmin(MultipartFile files, HttpServletRequest request, HttpServletResponse response, Courses record);

	int updateByAdmin(Courses record);

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
