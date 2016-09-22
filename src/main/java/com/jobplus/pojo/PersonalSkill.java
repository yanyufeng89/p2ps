package com.jobplus.pojo;

import java.io.Serializable;
import java.util.Date;

public class PersonalSkill  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7421459227783254629L;

	private Integer id;

    private Integer userid;

    private Integer isendorsements;

    private Integer endorsementsme;

    private Integer endorsementsother;

    private Date createtime;

    private Date updatetime;

    private String extendinfo;

    private String extendinfo2;

    private String extendinfo3;

    private String extendinfo4;

    private String extendinfo5;

    private String skillitem;

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

    public Integer getIsendorsements() {
        return isendorsements;
    }

    public void setIsendorsements(Integer isendorsements) {
        this.isendorsements = isendorsements;
    }

    public Integer getEndorsementsme() {
        return endorsementsme;
    }

    public void setEndorsementsme(Integer endorsementsme) {
        this.endorsementsme = endorsementsme;
    }

    public Integer getEndorsementsother() {
        return endorsementsother;
    }

    public void setEndorsementsother(Integer endorsementsother) {
        this.endorsementsother = endorsementsother;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getExtendinfo() {
        return extendinfo;
    }

    public void setExtendinfo(String extendinfo) {
        this.extendinfo = extendinfo == null ? null : extendinfo.trim();
    }

    public String getExtendinfo2() {
        return extendinfo2;
    }

    public void setExtendinfo2(String extendinfo2) {
        this.extendinfo2 = extendinfo2 == null ? null : extendinfo2.trim();
    }

    public String getExtendinfo3() {
        return extendinfo3;
    }

    public void setExtendinfo3(String extendinfo3) {
        this.extendinfo3 = extendinfo3 == null ? null : extendinfo3.trim();
    }

    public String getExtendinfo4() {
        return extendinfo4;
    }

    public void setExtendinfo4(String extendinfo4) {
        this.extendinfo4 = extendinfo4 == null ? null : extendinfo4.trim();
    }

    public String getExtendinfo5() {
        return extendinfo5;
    }

    public void setExtendinfo5(String extendinfo5) {
        this.extendinfo5 = extendinfo5 == null ? null : extendinfo5.trim();
    }

    public String getSkillitem() {
        return skillitem;
    }

    public void setSkillitem(String skillitem) {
        this.skillitem = skillitem == null ? null : skillitem.trim();
    }
}