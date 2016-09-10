package com.jobplus.pojo;

/**
 * 分享内容6大类枚举定义
 * <p>
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 *
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @version 1.0 <br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @creatdate Aug 19, 2016 2:58:47 PM <br>
 */
public enum DataType {

    DOC("DOC"),        //文档
    TOPICS("TOPICS"),    //话题
    BOOK("BOOK"),        //书籍
    ARTICLE("ARTICLE"),    //文章
    COURSES("COURSES"),    //课程
    SITES("SITES"),    //站点

    WEIBO("weibo"),        //微博
    WECHAT("wechat"),    //微信
    QQ("qq");        //QQ
    private String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
