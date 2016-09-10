package com.jobplus.pojo;

import java.io.Serializable;

public class ReportInfoConfig  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5381938781924271898L;

	private Integer id;

    private Integer parentid;

    private String reportinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getReportinfo() {
        return reportinfo;
    }

    public void setReportinfo(String reportinfo) {
        this.reportinfo = reportinfo == null ? null : reportinfo.trim();
    }
}