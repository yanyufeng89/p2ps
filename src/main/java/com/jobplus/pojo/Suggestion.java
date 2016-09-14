package com.jobplus.pojo;

import java.util.Date;

public class Suggestion extends PageParent{
    private Integer id;

    private Integer suguserid;

    private String sugtel;

    private String sugemail;

    private Date sugtime;

    private Integer isdeal;

    private Integer sugcauseid;

    private String sugcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuguserid() {
        return suguserid;
    }

    public void setSuguserid(Integer suguserid) {
        this.suguserid = suguserid;
    }

    public String getSugtel() {
        return sugtel;
    }

    public void setSugtel(String sugtel) {
        this.sugtel = sugtel == null ? null : sugtel.trim();
    }

    public String getSugemail() {
        return sugemail;
    }

    public void setSugemail(String sugemail) {
        this.sugemail = sugemail == null ? null : sugemail.trim();
    }

    public Date getSugtime() {
        return sugtime;
    }

    public void setSugtime(Date sugtime) {
        this.sugtime = sugtime;
    }

    public Integer getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Integer isdeal) {
        this.isdeal = isdeal;
    }

    public Integer getSugcauseid() {
        return sugcauseid;
    }

    public void setSugcauseid(Integer sugcauseid) {
        this.sugcauseid = sugcauseid;
    }

    public String getSugcontent() {
        return sugcontent;
    }

    public void setSugcontent(String sugcontent) {
        this.sugcontent = sugcontent == null ? null : sugcontent.trim();
    }
}