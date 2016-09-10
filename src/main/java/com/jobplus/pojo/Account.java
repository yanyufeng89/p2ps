package com.jobplus.pojo;

import java.io.Serializable;

public class Account implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8119395479142246500L;

	private Integer id;

    private Integer userid;

    private Integer jiamoney;

    private java.sql.Timestamp createtime;

    private Integer points;

    private Integer freezeup;

    /**
     * 
     */
    private String[] MONEYTYPES = {"points","jiaMoney"}; 
    
    /**
     * 对应更改表中的字段名
     */
    private String moneyType; 
    
    /**
     * 加减分值  正负值
     */
    private Integer score;
    /**
     * 分值枚举 []SCORES = {1,2,3,4,5,6,7,8,9,10,15,20};
     */
    private Integer []SCORES = {1,2,3,4,5,6,7,8,9,10,15,20};
    
    /**
     * 分值枚举 []SCORES = {1,2,3,4,5,6,7,8,9,10,15,20};
     */
    public Integer[] getSCORES() {
		return SCORES;
	}

	public Integer getId() {
        return id;
    }

    /**
     * MONEYTYPES = {"points","jiaMoney"}; 
     */
    public String[] getMONEYTYPES() {
		return MONEYTYPES;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }
    /**
     * 对应更改表中的字段名
     */
    public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getJiamoney() {
        return jiamoney;
    }

    public void setJiamoney(Integer jiamoney) {
        this.jiamoney = jiamoney;
    }


    public java.sql.Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.sql.Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
    		this.points = points;
    }

    public Integer getFreezeup() {
        return freezeup;
    }

    public void setFreezeup(Integer freezeup) {
        this.freezeup = freezeup;
    }
}