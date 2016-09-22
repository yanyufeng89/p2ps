package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Courses;

public interface CoursesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Courses record);

    int insertSelective(Courses record);

    Courses selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Courses record);

    int updateByPrimaryKeyWithBLOBs(Courses record);

    int updateByPrimaryKey(Courses record);
    
    /**
     * @param record
     * @return
     */
    Courses selectByRecord(Courses record);
    /**
     * 阅读数 ++ 
     * @param id
     * @return
     */
    int updateReadSum(Integer id);
    
    /**
     * 我分享的课程列表
     * @param record
     * @return
     */
    public List<Courses> getSharedCourseList(Courses record); 
    int getSharedCourseListCount(Courses record); 
    
    /**
     * 我收藏的课程列表
     * @param record
     * @return
     */
    public List<Courses> getCollectedCourseList(Courses record); 
    int getCollectedCourseListCount(Courses record); 
    
    /**
     * 批量删除我分享的书籍
     * @param condition
     * @return
     */
    public int delSharedCourses(@Param(value="condition")String[] condition);
}