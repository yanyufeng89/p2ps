package com.jobplus.service;

import com.jobplus.pojo.CoursesShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;

public interface ICoursesShareService {

	int deleteByPrimaryKey(Integer id);

    int insert(CoursesShare record);
    
    int insertSelective(CoursesShare record);

    CoursesShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoursesShare record);

    int updateByPrimaryKey(CoursesShare record);
    
    CoursesShare insertAndReturnRecord(CoursesShare record,String contextPath,User user);
    /**
     * 获取评论列表
     * @param record
     * @return
     */
    Page<CoursesShare> getList(CoursesShare record);
    /**
     * @param record
     * @return
     */
    int delComment(CoursesShare record);
}
