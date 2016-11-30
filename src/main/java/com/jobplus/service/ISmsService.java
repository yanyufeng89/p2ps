package com.jobplus.service;

import java.util.List;

import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;

public interface ISmsService {

    int deleteByPrimaryKey(Integer id);

    int insert(Sms record);
    
    /**
     * 发送私信并且推送通知给对方
     * @param record
     * @return
     */
    int insertSmsAndNotice(Sms record);

    int insertSelective(Sms record);

    Sms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKeyWithBLOBs(Sms record);

//    int updateByPrimaryKey(Sms record);
    
    /**
     * 插入私信
     */
    int insertPrvivatSms(Sms record);
    /**
     *  添加通知    
     * //senderid   receivedid  smstype 
     * 消息类型   0系统消息    1私信  2关注通知(成为你的粉丝)   
     * 11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复     
     * 21书籍推荐新增回复   
     * 31课程新增推荐  32课程新增评论 
     * 41文章新增推荐   42文章新增评论 
     * 51站点新增推荐   52站点新增评论 
     * 61文档新增推荐   62文档新增评论
     * //TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",5
		// "tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
     * @param req
     * @param sms
     * @return
     */
    /**
     * @param user 发送消息的人
     * @param contextPath 系统相对路径 request.getContextPath()
     * @param tableName  为了拼接 详情URL  urlMap.get(tableName)
     * //TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",5
		// "tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
     * 	urlMap.put("tbl_user","");
		urlMap.put("tbl_topics","/topics/getTopicsDetail/");
		urlMap.put("tbl_topics_comment","/topics/getTopicsDetail/");		
		urlMap.put("tbl_topics_isLiked","/topics/getTopicsDetail/");		
		urlMap.put("tbl_courses","/courses/getCourseDetail/");
		urlMap.put("tbl_courses_share","/courses/getCourseDetail/");		
		urlMap.put("tbl_courses_sLiked","/courses/getCourseDetail/");		
		urlMap.put("tbl_sites","/sites/getSiteDetail/");
		urlMap.put("tbl_sites_share","/sites/getSiteDetail/");		
		urlMap.put("tbl_sites_isliked","/sites/getSiteDetail/");		
		urlMap.put("tbl_article","/article/getArticleDetail/");
		urlMap.put("tbl_article_share","/article/getArticleDetail/");		
		urlMap.put("tbl_article_isLiked","/article/getArticleDetail/");		
		urlMap.put("tbl_docs","/docs/getDocsDetail/");
		urlMap.put("tbl_docs_comment","/docs/getDocsDetail/");
		urlMap.put("tbl_books","/books/getBookDetail/");		
		urlMap.put("tbl_books_share","/books/getBookDetail/");
		urlMap.put("tbl_books_isLiked","/books/getBookDetail/");
		urlMap.put("tbl_docs_isLiked","/docs/getDocsDetail/");
		
		//企业快讯
		urlMap.put("tbl_company_news","/myHome/getHomePage/");
     * @param topObjId  文档、书籍、课程等 id
     * @param objCreateperson 文档、书籍、课程等创作人 id
     * @param smsType 消息类型   0系统消息    1私信  2关注通知(成为你的粉丝) 
     * @param relationid 关联主体：文档、文档评论,书籍、话题评论、课程等 id，存入tbl_sms 表
     * @param ObjectName 文档、书籍、课程等名字
     * @param content  系统消息内容或者是 消息内容尾部拼接内容
     * @return
     */
    int addNotice(User user, String contextPath,String tableName,Integer topObjId,Integer objCreateperson,Integer smsType,Integer relationid,String ObjectName,String content);
    
    
    /**
     * 新增消息提醒,先过滤
     *  消息格式 ：user.name + userUrl + content + objUrl + content2
     *  赞了你分享的文章  <a target='_blank' href='/article/getArticleDetail/58'>头条新闻_东方头条</a>
     * @param user 触发消息的人(id,name)
     * @param userUrl
     * @param content eg:赞了你分享的文章 
     * @param objUrl <a target='_blank' href='/article/getArticleDetail/58'>头条新闻_东方头条</a>
     * @param content2 ..
     * @return
     */ // FIXME  待完善
//    int addNotices(User user,String userUrl,String content,String objUrl,String content2);
    
    
    /**
     * 获取所有消息
     * @param record
     * @return
     */
    Page<Sms> getAllSms(Sms record);
    
    /**
     * 获取所有未读通知条数  未读消息总数(除私信)
     */
    int getNewSmsSum(Integer userid);
    /**
     * 获取所有未读私信条数
     */
    int getPrivateSmsSum(Integer userid);

	/**
	 * 我的消息总数 (未读、已读  消息&&私信)
	 * 
	 * @param userid
	 * @return
	 */
	int geSmsSum(Integer userid);

    /**
     * 弹窗显示未读消息列表
     * @param record
     * @return
     */
    List<Sms> getNewSms(Sms record);
    /**
     * 阅读私信   更改阅读状态为已读
     * @param record
     * @return
     */
    Sms getSmsDetail(Sms record);
    
    /**
     * 批量标记为已读        传递ID即为单个,否则全部
     * @return
     */
    public int makeReadSms(Sms record);
    /**
     * 批量删除        传递ID即为单个,否则全部
     * @return
     */
    public int delSms(Sms record);
    
    
    /**
     * 发送系统通知
     */
    public int sendSysNotice(Integer userid,String content);
}