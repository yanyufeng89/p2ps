package com.jobplus.pojo;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class User extends PageParent implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8854530387699124506L;

	private Integer userid;

    private String username;

    private String email;

    private String mobile;

    private String tel;

    private String passwd;

    private Integer gender;

    private Integer age;

    private String addres;

    private String identityname;

    private String identityno;

    private Integer serviceyear;

    private String industry;

    private String position;

    private String education;

    private String school;

    private String specialty;

    private String description;

    private String headicon;

    private String compname;

    private String city;

    private Integer usertype;

    private Integer userlevel;

    private Integer authenticate;

    private Integer isvalid;

    private Integer issearch;

    private java.sql.Timestamp createtime;

    private java.sql.Timestamp updatetime;
    
    private String fansIds;
    
    private String nation;

    private String province;

    private Integer ismarry;

    private String birthdayone;

    private String birthdaytwo;
    
    /**
     * 联系邮箱
     */
    private String contactEmail ;
    /**
     * 联系号码
     */
    private String contactTel;
    
    private CommonsMultipartFile headIconFile;  
    
    /**
     * 新增企业用户的快讯条数
     */
    private Integer cpNewsSum;
    
	public String getContactEmail() {
		return contactEmail;
	}

	public Integer getCpNewsSum() {
		return cpNewsSum;
	}

	public void setCpNewsSum(Integer cpNewsSum) {
		this.cpNewsSum = cpNewsSum;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public CommonsMultipartFile getHeadIconFile() {
		return headIconFile;
	}

	public void setHeadIconFile(CommonsMultipartFile headIconFile) {
		this.headIconFile = headIconFile;
	}

	/**
	 * this method get the nation of value
	 * 
	 * @return Returns the nation.
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * this method set the nation of value
	 * 
	 * @param enName The nation to set.
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * this method get the province of value
	 * 
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * this method set the province of value
	 * 
	 * @param enName The province to set.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * this method get the ismarry of value
	 * 
	 * @return Returns the ismarry.
	 */
	public Integer getIsmarry() {
		return ismarry;
	}

	/**
	 * this method set the ismarry of value
	 * 
	 * @param enName The ismarry to set.
	 */
	public void setIsmarry(Integer ismarry) {
		this.ismarry = ismarry;
	}

	/**
	 * this method get the birthdayone of value
	 * 
	 * @return Returns the birthdayone.
	 */
	public String getBirthdayone() {
		return birthdayone;
	}

	/**
	 * this method set the birthdayone of value
	 * 
	 * @param enName The birthdayone to set.
	 */
	public void setBirthdayone(String birthdayone) {
		this.birthdayone = birthdayone;
	}

	/**
	 * this method get the birthdaytwo of value
	 * 
	 * @return Returns the birthdaytwo.
	 */
	public String getBirthdaytwo() {
		return birthdaytwo;
	}

	/**
	 * this method set the birthdaytwo of value
	 * 
	 * @param enName The birthdaytwo to set.
	 */
	public void setBirthdaytwo(String birthdaytwo) {
		this.birthdaytwo = birthdaytwo;
	}

	/**
     * 用于查询用户的数据
     */
    private OperationSum operationSum;

    public OperationSum getOperationSum() {
		return operationSum;
	}

	public void setOperationSum(OperationSum operationSum) {
		this.operationSum = operationSum;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres == null ? null : addres.trim();
    }

    public String getIdentityname() {
        return identityname;
    }

    public void setIdentityname(String identityname) {
        this.identityname = identityname == null ? null : identityname.trim();
    }

    public String getIdentityno() {
        return identityno;
    }

    public void setIdentityno(String identityno) {
        this.identityno = identityno == null ? null : identityno.trim();
    }

    public Integer getServiceyear() {
        return serviceyear;
    }

    public void setServiceyear(Integer serviceyear) {
        this.serviceyear = serviceyear;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon == null ? null : headicon.trim();
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname == null ? null : compname.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Integer getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Integer authenticate) {
        this.authenticate = authenticate;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getIssearch() {
        return issearch;
    }

    public void setIssearch(Integer issearch) {
        this.issearch = issearch;
    }

	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public java.sql.Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.sql.Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getFansIds() {
		return fansIds;
	}

	public void setFansIds(String fansIds) {
		this.fansIds = fansIds;
	}
}