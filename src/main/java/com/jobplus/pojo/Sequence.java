package com.jobplus.pojo;

import java.io.Serializable;

public class Sequence implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4347120496853655462L;

	private String name;

    private Integer currentVal;

    private Integer incrementVal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(Integer currentVal) {
        this.currentVal = currentVal;
    }

    public Integer getIncrementVal() {
        return incrementVal;
    }

    public void setIncrementVal(Integer incrementVal) {
        this.incrementVal = incrementVal;
    }
}