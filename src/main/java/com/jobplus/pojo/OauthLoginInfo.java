package com.jobplus.pojo;

import java.io.Serializable;

public class OauthLoginInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5973391140693417025L;

	private Integer id;

    private Integer userid;

    private String oauthname;

    private String oauthopenid;

    private String oauthaccesstoken;

    private Integer oauthexpires;

    private String nickname;

    private String headicon;

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

    public String getOauthname() {
        return oauthname;
    }

    public void setOauthname(String oauthname) {
        this.oauthname = oauthname == null ? null : oauthname.trim();
    }

    public String getOauthopenid() {
        return oauthopenid;
    }

    public void setOauthopenid(String oauthopenid) {
        this.oauthopenid = oauthopenid == null ? null : oauthopenid.trim();
    }

    public String getOauthaccesstoken() {
        return oauthaccesstoken;
    }

    public void setOauthaccesstoken(String oauthaccesstoken) {
        this.oauthaccesstoken = oauthaccesstoken == null ? null : oauthaccesstoken.trim();
    }

    public Integer getOauthexpires() {
        return oauthexpires;
    }

    public void setOauthexpires(Integer oauthexpires) {
        this.oauthexpires = oauthexpires;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon;
    }
}