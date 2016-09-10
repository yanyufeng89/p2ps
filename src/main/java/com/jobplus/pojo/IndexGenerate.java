package com.jobplus.pojo;

import java.io.Serializable;

public class IndexGenerate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2230165803586223828L;
	
	private Integer id;
	
    private Integer relationid;

    private String relationtype;

    private String indexcont1;

    private String indexcont2;

    private String indexcont3;

    private String indexcont4;

    private String indexcont5;

    private String indexcont6;

    private String indexcont7;

    private String indexcont8;

    private Integer indexstatus;

    private java.sql.Timestamp createtime;

    private java.sql.Timestamp indextime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationid() {
        return relationid;
    }

    public void setRelationid(Integer relationid) {
        this.relationid = relationid;
    }

    public String getRelationtype() {
        return relationtype;
    }

    public void setRelationtype(String relationtype) {
        this.relationtype = relationtype == null ? null : relationtype.trim();
    }

    public String getIndexcont1() {
        return indexcont1;
    }

    public void setIndexcont1(String indexcont1) {
        this.indexcont1 = indexcont1 == null ? null : indexcont1.trim();
    }

    public String getIndexcont2() {
        return indexcont2;
    }

    public void setIndexcont2(String indexcont2) {
        this.indexcont2 = indexcont2 == null ? null : indexcont2.trim();
    }

    public String getIndexcont3() {
        return indexcont3;
    }

    public void setIndexcont3(String indexcont3) {
        this.indexcont3 = indexcont3 == null ? null : indexcont3.trim();
    }

    public String getIndexcont4() {
        return indexcont4;
    }

    public void setIndexcont4(String indexcont4) {
        this.indexcont4 = indexcont4 == null ? null : indexcont4.trim();
    }

    public String getIndexcont5() {
        return indexcont5;
    }

    public void setIndexcont5(String indexcont5) {
        this.indexcont5 = indexcont5 == null ? null : indexcont5.trim();
    }

    public String getIndexcont6() {
        return indexcont6;
    }

    public void setIndexcont6(String indexcont6) {
        this.indexcont6 = indexcont6 == null ? null : indexcont6.trim();
    }

    public String getIndexcont7() {
        return indexcont7;
    }

    public void setIndexcont7(String indexcont7) {
        this.indexcont7 = indexcont7 == null ? null : indexcont7.trim();
    }

    public String getIndexcont8() {
        return indexcont8;
    }

    public void setIndexcont8(String indexcont8) {
        this.indexcont8 = indexcont8 == null ? null : indexcont8.trim();
    }

    public Integer getIndexstatus() {
        return indexstatus;
    }

    public void setIndexstatus(Integer indexstatus) {
        this.indexstatus = indexstatus;
    }

	public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public java.sql.Timestamp getIndextime() {
		return indextime;
	}

	public void setIndextime(java.sql.Timestamp indextime) {
		this.indextime = indextime;
	}

}