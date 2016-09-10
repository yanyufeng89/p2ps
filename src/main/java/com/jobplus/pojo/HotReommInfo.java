package com.jobplus.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class HotReommInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4694882394912568790L;

	private Integer id;

    private String title;

    private String shreadtype;

    private String datatype;

    private String tags;

    private String imgurl;

    private String linkurl;

    private Integer dataid;

    private Timestamp lastedittime;

    private Integer createuser;

    private Integer kpi;

    private Integer kpi2;

    private Integer kpi3;

    private String extendinfo;

    private String extendinfo2;

    private String extendinfo3;

    private String extendinfo4;

    private String extendinfo5;

    private Timestamp createtime;

    private String intro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getShreadtype() {
        return shreadtype;
    }

    public void setShreadtype(String shreadtype) {
        this.shreadtype = shreadtype == null ? null : shreadtype.trim();
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype == null ? null : datatype.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public Timestamp getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(Timestamp lastedittime) {
        this.lastedittime = lastedittime;
    }

    public Integer getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public Integer getKpi() {
        return kpi;
    }

    public void setKpi(Integer kpi) {
        this.kpi = kpi;
    }

    public Integer getKpi2() {
        return kpi2;
    }

    public void setKpi2(Integer kpi2) {
        this.kpi2 = kpi2;
    }

    public Integer getKpi3() {
        return kpi3;
    }

    public void setKpi3(Integer kpi3) {
        this.kpi3 = kpi3;
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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}