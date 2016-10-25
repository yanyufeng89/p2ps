package com.jobplus.pojo;

import java.io.Serializable;

public class SchoolInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3317390280425085556L;

	private Integer id;

    private String schoolname;

    private String schoollogo;

    private String schoolurl;

    private String schoolcity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    public String getSchoollogo() {
        return schoollogo;
    }

    public void setSchoollogo(String schoollogo) {
        this.schoollogo = schoollogo == null ? null : schoollogo.trim();
    }

    public String getSchoolurl() {
        return schoolurl;
    }

    public void setSchoolurl(String schoolurl) {
        this.schoolurl = schoolurl == null ? null : schoolurl.trim();
    }

    public String getSchoolcity() {
        return schoolcity;
    }

    public void setSchoolcity(String schoolcity) {
        this.schoolcity = schoolcity == null ? null : schoolcity.trim();
    }
}