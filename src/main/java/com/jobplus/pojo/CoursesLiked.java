package com.jobplus.pojo;

import java.io.Serializable;

public class CoursesLiked extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7525365809010993669L;

	private Integer userid;

    private Integer coursesid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCoursesid() {
        return coursesid;
    }

    public void setCoursesid(Integer coursesid) {
        this.coursesid = coursesid;
    }
}