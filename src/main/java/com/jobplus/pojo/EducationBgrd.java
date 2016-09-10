package com.jobplus.pojo;

import java.io.Serializable;

public class EducationBgrd implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5343995947966673173L;

	private Integer id;

    private Integer userid;

    private String school;

    private String major;

    private String degree;

    private String description;

    private java.sql.Timestamp starttime;

    private java.sql.Timestamp endtime;

    private java.sql.Timestamp createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public java.sql.Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(java.sql.Timestamp starttime) {
		this.starttime = starttime;
	}

	public java.sql.Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(java.sql.Timestamp endtime) {
		this.endtime = endtime;
	}

	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

}