package com.jobplus.pojo;

import java.io.Serializable;

public class ReportInfo extends PageParent implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1541421277987757597L;

	private Integer id;

    /**
     * 举报人id
     */
    private Integer reportuserid;

    /**
     * 举报人电话
     */
    private String reporttel;

    /**
     * 被举报人id
     */
    private Integer reportbyuserid;

    /**
     * 举报对象id
     */
    private Integer reporttargetid;

    /**
     * 举报类型
     */
    private String reporttype;

    private java.sql.Timestamp reporttime;

    /**
     * 是否处理
     */
    private Integer isdeal;

    /**
     * 举报理由
     */
    private String reportcause;
    /**
     * 举报理由ID
     */
    private Integer reportcauseid;
    
    /**
     * 举报类型枚举  对应 表名   "tbl_user","tbl_article","tbl_books","tbl_courses","tbl_docs","tbl_topics","tbl_topics_comment" REPORTTYPE_INDEX
     */
    private String[] REPORTTYPES = {"tbl_user","tbl_article_share","tbl_books_share","tbl_courses_share","tbl_docs_comment","tbl_topics_comment","tbl_sites_share"//6
    		,"tbl_article","tbl_books","tbl_courses","tbl_docs","tbl_topics","tbl_sites"};    
    /**
     * 用于定义枚举类的下标  
     */
    private Integer REPORTTYPE_INDEX;
    

	public String[] getREPORTTYPES() {
		return REPORTTYPES;
	}

	public void setREPORTTYPES(String[] rEPORTTYPES) {
		REPORTTYPES = rEPORTTYPES;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportuserid() {
        return reportuserid;
    }

    public void setReportuserid(Integer reportuserid) {
        this.reportuserid = reportuserid;
    }

    public String getReporttel() {
        return reporttel;
    }

    public Integer getREPORTTYPE_INDEX() {
		return REPORTTYPE_INDEX;
	}

	public void setREPORTTYPE_INDEX(Integer rEPORTTYPE_INDEX) {
		REPORTTYPE_INDEX = rEPORTTYPE_INDEX;
	}

	public void setReporttel(String reporttel) {
        this.reporttel = reporttel;
    }

    public Integer getReportbyuserid() {
        return reportbyuserid;
    }

    public void setReportbyuserid(Integer reportbyuserid) {
        this.reportbyuserid = reportbyuserid;
    }

    public Integer getReporttargetid() {
        return reporttargetid;
    }

    public void setReporttargetid(Integer reporttargetid) {
        this.reporttargetid = reporttargetid;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    public java.sql.Timestamp getReporttime() {
		return reporttime;
	}

	public void setReporttime(java.sql.Timestamp reporttime) {
		this.reporttime = reporttime;
	}

	public Integer getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Integer isdeal) {
        this.isdeal = isdeal;
    }

    public String getReportcause() {
        return reportcause;
    }

    public void setReportcause(String reportcause) {
        this.reportcause = reportcause == null ? null : reportcause.trim();
    }

	/**
	 * this method get the reportcauseid of value
	 * 
	 * @return Returns the reportcauseid.
	 */
	public Integer getReportcauseid() {
		return reportcauseid;
	}

	/**
	 * this method set the reportcauseid of value
	 * 
	 * @param enName The reportcauseid to set.
	 */
	public void setReportcauseid(Integer reportcauseid) {
		this.reportcauseid = reportcauseid;
	}
}