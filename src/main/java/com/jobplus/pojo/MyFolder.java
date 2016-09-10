package com.jobplus.pojo;

import java.io.Serializable;

public class MyFolder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7888909506940731563L;

	private Integer id;

    private Integer userid;

    private Integer rootfolderid;

    private Integer parentfolderid;

    private String foldername;

    private String foldertype;

    private String foldertire;

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

    public Integer getRootfolderid() {
        return rootfolderid;
    }

    public void setRootfolderid(Integer rootfolderid) {
        this.rootfolderid = rootfolderid;
    }

    public Integer getParentfolderid() {
        return parentfolderid;
    }

    public void setParentfolderid(Integer parentfolderid) {
        this.parentfolderid = parentfolderid;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername == null ? null : foldername.trim();
    }

    public String getFoldertype() {
        return foldertype;
    }

    public void setFoldertype(String foldertype) {
        this.foldertype = foldertype == null ? null : foldertype.trim();
    }

    public String getFoldertire() {
        return foldertire;
    }

    public void setFoldertire(String foldertire) {
        this.foldertire = foldertire == null ? null : foldertire.trim();
    }
}