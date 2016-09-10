package com.jobplus.pojo;

/**
 * 消息辅助类 父类
 * @author Jerry
 * 2016年7月30日下午2:56:59
 *
 */
public class SmsParent {

	// 用于消息入库用 站点标题
	private String objTitle;
	// 站点创建人id
	private Integer objCreateperson;

	public String getObjTitle() {
		return objTitle;
	}

	public void setObjTitle(String objTitle) {
		this.objTitle = objTitle;
	}

	public Integer getObjCreateperson() {
		return objCreateperson;
	}

	public void setObjCreateperson(Integer objCreateperson) {
		this.objCreateperson = objCreateperson;
	}

}
