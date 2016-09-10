package com.jobplus.pojo;

import java.io.Serializable;

public class WorkExper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5690719915350067431L;

	private Integer id;

	private Integer userid;

	private String company;

	private String industry;

	private String department;

	private String jobtitle;

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle == null ? null : jobtitle.trim();
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