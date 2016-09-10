package com.jobplus.pojo;

import java.io.Serializable;

/**
 * 用户操作统计表
 *
 */
public class OperationSum implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6108666390953291932L;

	private Integer userid;

    private Integer docsharesum;

    private Integer docdownsum;

    private Integer doccollsum;

    private Integer topicssharesum;

    private Integer topicscomsum;

    private Integer topicsattesum;

    private Integer booksharesum;

    private Integer bookcollsum;

    private Integer articlesharesum;

    private Integer articlecollsum;

    private Integer coursessharesum;

    private Integer coursescollsum;

    private Integer sitessharesum;

    private Integer sitescollsum;
    
    /**
     * 我关注的个数
     */
    private Integer attentionsum;
    
    /**
     * 粉丝数
     */
    private Integer fanssum;

    private java.sql.Timestamp operatortime;    
    
    /**
     * 所有的分享总数
     */
    private Integer allshresum;
    
    
    
    /**
     * 对应operatinnsum表字段 
     */
    private String tableColum;
	private String[] TABLECOLUM = { "docShareSum", "docDownSum", "docCollSum", 
								"topicsShareSum", "topicsComSum","topicsAtteSum",
								"bookShareSum", "bookCollSum", 
								"articleShareSum", "articleCollSum", "coursesShareSum",
								"coursesCollSum", "sitesShareSum", "sitesCollSum", "attentionSum", "fansSum", };
    /**
     * 是增加还是减少
     */
    private String addOrDecrease;
    private String []ADDORDECREASE = {"+","-"};
    public String[] getADDORDECREASE() {
		return ADDORDECREASE;
	}

	/**
     * 增加 减少数量
     */
    private Integer AdOrDeNum;
    
    public OperationSum(){
    	this.docsharesum=0;
    	this.docdownsum=0;
    	this.doccollsum=0;
    	this.topicssharesum=0;
    	this.topicscomsum=0;
    	this.topicsattesum=0;
    	this.booksharesum=0;
    	this.bookcollsum=0;
    	this.articlesharesum=0;
    	this.articlecollsum=0;
    	this.coursessharesum=0;
    	this.coursescollsum=0;
    	this.sitessharesum=0;
    	this.sitescollsum=0;
    }

    public String[] getTABLECOLUM() {
		return TABLECOLUM;
	}

	public String getTableColum() {
		return tableColum;
	}

	public void setTableColum(String tableColum) {
		this.tableColum = tableColum;
	}

	public String getAddOrDecrease() {
		return addOrDecrease;
	}

	public void setAddOrDecrease(String addOrDecrease) {
		this.addOrDecrease = addOrDecrease;
	}

	public Integer getAdOrDeNum() {
		return AdOrDeNum;
	}

	public void setAdOrDeNum(Integer adOrDeNum) {
		AdOrDeNum = adOrDeNum;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDocsharesum() {
        return docsharesum;
    }

    public Integer getAllshresum() {
		return allshresum;
	}

	public void setAllshresum(Integer allshresum) {
		this.allshresum = allshresum;
	}

	public void setDocsharesum(Integer docsharesum) {
        this.docsharesum = docsharesum;
    }

    public Integer getDocdownsum() {
        return docdownsum;
    }

    public Integer getAttentionsum() {
		return attentionsum;
	}

	public void setAttentionsum(Integer attentionsum) {
		this.attentionsum = attentionsum;
	}

	public Integer getFanssum() {
		return fanssum;
	}

	public void setFanssum(Integer fanssum) {
		this.fanssum = fanssum;
	}

	public void setDocdownsum(Integer docdownsum) {
        this.docdownsum = docdownsum;
    }

    public Integer getDoccollsum() {
        return doccollsum;
    }

    public void setDoccollsum(Integer doccollsum) {
        this.doccollsum = doccollsum;
    }

    public Integer getTopicssharesum() {
        return topicssharesum;
    }

    public void setTopicssharesum(Integer topicssharesum) {
        this.topicssharesum = topicssharesum;
    }

    public Integer getTopicscomsum() {
        return topicscomsum;
    }

    public void setTopicscomsum(Integer topicscomsum) {
        this.topicscomsum = topicscomsum;
    }

    public Integer getTopicsattesum() {
        return topicsattesum;
    }

    public void setTopicsattesum(Integer topicsattesum) {
        this.topicsattesum = topicsattesum;
    }

    public Integer getBooksharesum() {
        return booksharesum;
    }

    public void setBooksharesum(Integer booksharesum) {
        this.booksharesum = booksharesum;
    }

    public Integer getBookcollsum() {
        return bookcollsum;
    }

    public void setBookcollsum(Integer bookcollsum) {
        this.bookcollsum = bookcollsum;
    }

    public Integer getArticlesharesum() {
        return articlesharesum;
    }

    public void setArticlesharesum(Integer articlesharesum) {
        this.articlesharesum = articlesharesum;
    }

    public Integer getArticlecollsum() {
        return articlecollsum;
    }

    public void setArticlecollsum(Integer articlecollsum) {
        this.articlecollsum = articlecollsum;
    }

    public Integer getCoursessharesum() {
        return coursessharesum;
    }

    public void setCoursessharesum(Integer coursessharesum) {
        this.coursessharesum = coursessharesum;
    }

    public Integer getCoursescollsum() {
        return coursescollsum;
    }

    public void setCoursescollsum(Integer coursescollsum) {
        this.coursescollsum = coursescollsum;
    }

    public Integer getSitessharesum() {
        return sitessharesum;
    }

    public void setSitessharesum(Integer sitessharesum) {
        this.sitessharesum = sitessharesum;
    }

    public Integer getSitescollsum() {
        return sitescollsum;
    }

    public void setSitescollsum(Integer sitescollsum) {
        this.sitescollsum = sitescollsum;
    }

	public java.sql.Timestamp getOperatortime() {
		return operatortime;
	}

	public void setOperatortime(java.sql.Timestamp operatortime) {
		this.operatortime = operatortime;
	}

}