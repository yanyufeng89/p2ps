package com.jobplus.pojo;

/**
 * 父类 page辅助
 * 
 * @author Jerry 2016年7月21日下午3:56:57
 *
 */
public class PageParent {

	private Integer pageNo; // 当前页码
	private Integer pageSize;// Integer.valueOf(Global.getConfig("page.pageSize"));
	
	private Integer defaultPageSize = 10;// FIXME 默认显示条数
	
	private long pageCount;// 总记录数，设置为“-1”表示不查询总数
	private long limitSt;// limit起始参数

	
/*************************用于消息*********************************/	
	/**
	 * // 用于消息入库用 对象名称
	 */
	private String objectNamePg;
	
	/**
	 * // 用于消息入库用  对象创建人 
	 */
	private Integer objCreatepersonPg;
	
	 //关联主体ID
    private Integer relationidPg;
    
    /**
     * 顶端obj  Id  如话题id,书籍Id
     */
    private Integer topObjId;
    /*************************用于消息*********************************/	    


	public String getObjectNamePg() {
		return objectNamePg;
	}

	public void setObjectNamePg(String objectNamePg) {
		this.objectNamePg = objectNamePg;
	}

	public Integer getObjCreatepersonPg() {
		return objCreatepersonPg;
	}

	public Integer getRelationidPg() {
		return relationidPg;
	}

	public void setRelationidPg(Integer relationidPg) {
		this.relationidPg = relationidPg;
	}

	public void setObjCreatepersonPg(Integer objCreatepersonPg) {
		this.objCreatepersonPg = objCreatepersonPg;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getTopObjId() {
		return topObjId;
	}

	public void setTopObjId(Integer topObjId) {
		this.topObjId = topObjId;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(Integer defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}


	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getLimitSt() {
		return limitSt;
	}

	public void setLimitSt(long limitSt) {
		this.limitSt = limitSt;
	}

}
