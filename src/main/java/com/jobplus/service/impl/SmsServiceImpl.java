package com.jobplus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.jobplus.utils.WebSocketUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jobplus.dao.SmsMapper;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.SmsFilter;
import com.jobplus.pojo.User;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsFilterService;
import com.jobplus.service.ISmsService;
import com.jobplus.utils.DateUtils;

@Service("smsService")
public class SmsServiceImpl implements ISmsService{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	@Resource
	private SmsMapper smsDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private ISmsFilterService smsFilterService;
	
	/**消息类型   0系统消息    1私信  2关注通知(成为你的粉丝)                  2
	*11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
	*21书籍推荐新增回复   22书籍点赞										9
	*31课程新增推荐  32课程新增评论 	 33课程点赞							12
	*41文章新增推荐   42文章新增评论 	 43文章点赞							15
	*51站点新增推荐   52站点新增评论 	 53站点点赞							18
	*61文档新增推荐   62文档新增评论	 63文档点赞							21
	*3关注了你发布的话题  
    * */
	private static Map<String, String> map = new HashMap<String,String>();
	private static Map<String, String> urlMap = new HashMap<String,String>();
//	private static Map<String, String> tableColMap = new HashMap<String,String>();
	static {
		map.put("2","关注了您");
		map.put("11","回答了你分享的话题");
		map.put("12","回复了你话题的评论");
		map.put("13","评论了你的话题");
		map.put("14","回复了你的话题");
		map.put("15","赞了你的话题");
		map.put("21","回复了你分享的书籍");
		map.put("22","赞了你分享的书籍");
		map.put("31","推荐了你分享的课程");
		map.put("32","评论了你分享的课程");
		map.put("33","赞了你分享的课程");
		map.put("41","评论了你分享的文章");
		map.put("42","评论了你分享的文章");
		map.put("43","赞了你分享的文章");
		map.put("51","评论了你分享的站点");
		map.put("52","评论了你分享的站点");
		map.put("53","赞了你分享的站点");
		map.put("61","评论了你分享的文档");
		map.put("62","评论了你分享的文档");
		map.put("63","赞了你分享的文档");
		map.put("3","关注了你发布的话题");
//		[]TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites",5
//				"tbl_docs_comment tt right join tbl_docs t on tt.docId = t.ID",
//				"tbl_books_share tt right join tbl_books t on tt.bookID = t.ID",
//				"tbl_topics_comment tt right join tbl_topics t on tt.topicsID = t.ID",
//				"tbl_courses_share tt right join tbl_courses t on tt.coursesID = t.ID",
//				"tbl_article_share tt right join tbl_article t on tt.articleID = t.id",
//				"tbl_sites_share tt right join tbl_sites t on tt.siteID = t.id"};
		urlMap.put("tbl_user","");
		urlMap.put("tbl_topics","/topics/getTopicsDetail?topicId=");
		urlMap.put("tbl_topics_comment","/topics/getTopicsDetail?topicId=");		
		urlMap.put("tbl_topics_isLiked","/topics/getTopicsDetail?topicId=");		
		urlMap.put("tbl_courses","/courses/getCourseDetail?id=");
		urlMap.put("tbl_courses_share","/courses/getCourseDetail?id=");		
		urlMap.put("tbl_courses_sLiked","/courses/getCourseDetail?id=");		
		urlMap.put("tbl_sites","/sites/getSiteDetail?id=");
		urlMap.put("tbl_sites_share","/sites/getSiteDetail?id=");		
		urlMap.put("tbl_sites_isliked","/sites/getSiteDetail?id=");		
		urlMap.put("tbl_article","/article/getArticleDetail?id=");
		urlMap.put("tbl_article_share","/article/getArticleDetail?id=");		
		urlMap.put("tbl_article_isLiked","/article/getArticleDetail?id=");		
		urlMap.put("tbl_docs","/docs/getDocsDetail?id=");
		urlMap.put("tbl_docs_comment","/docs/getDocsDetail?id=");
		urlMap.put("tbl_books","/books/getBookDetail?id=");		
		urlMap.put("tbl_books_share","/books/getBookDetail?id=");
		urlMap.put("tbl_books_isLiked","/books/getBookDetail?id=");
		urlMap.put("tbl_docs_isLiked","/docs/getDocsDetail?id=");
		
		
		
//		urlMap.put("tbl_topics","/topics/getTopicsDetail?topicId=");
//		urlMap.put("tbl_topics_comment tt right join tbl_topics t on tt.topicsID = t.ID","/topics/getTopicsDetail?topicId=");		
//		urlMap.put("tbl_topics_isLiked tt right join tbl_topics t on tt.commId = t.ID","/topics/getTopicsDetail?topicId=");		
//		urlMap.put("tbl_courses","/courses/getCourseDetail?id=");
//		urlMap.put("tbl_courses_share tt right join tbl_courses t on tt.coursesID = t.ID","/courses/getCourseDetail?id=");		
//		urlMap.put("tbl_courses_sLiked tt right join tbl_courses t on tt.coursesId = t.ID","/courses/getCourseDetail?id=");		
//		urlMap.put("tbl_sites","/sites/getSiteDetail?id=");
//		urlMap.put("tbl_sites_share tt right join tbl_sites t on tt.siteID = t.id","/sites/getSiteDetail?id=");		
//		urlMap.put("tbl_sites_isliked tt right join tbl_sites t on tt.commId = t.id","/sites/getSiteDetail?id=");		
//		urlMap.put("tbl_article","/article/getArticleDetail?id=");
//		urlMap.put("tbl_article_share tt right join tbl_article t on tt.articleID = t.id","/article/getArticleDetail?id=");		
//		urlMap.put("tbl_article_isLiked tt right join tbl_article t on tt.articleID = t.id","/article/getArticleDetail?id=");		
//		urlMap.put("tbl_docs","/docs/getDocsDetail?id=");
//		urlMap.put("tbl_docs_comment tt right join tbl_docs t on tt.docId = t.ID","/docs/getDocsDetail?id=");
//		urlMap.put("tbl_books","/books/getBookDetail?id=");		
//		urlMap.put("tbl_books_share tt right join tbl_books t on tt.bookID = t.ID","/books/getBookDetail?id=");
//		urlMap.put("tbl_books_isLiked tt right join tbl_books t on tt.bookID = t.ID","/books/getBookDetail?id=");
		
//		tableColMap.put("tbl_topics","t.createPerson as receivedId ,t.title as objectName");
//		tableColMap.put("tbl_topics_comment tt right join tbl_topics t on tt.topicsID = t.ID","t.createPerson as receivedId ,t.title as objectName");		
//		tableColMap.put("tbl_topics_isLiked tt right join tbl_topics t on tt.commId = t.ID","t.createPerson as receivedId ,t.title as objectName");	
//		
//		tableColMap.put("tbl_courses","t.userId as receivedId ,t.coursesName as objectName");
//		tableColMap.put("tbl_courses_share tt right join tbl_courses t on tt.coursesID = t.ID","t.userId as receivedId ,t.coursesName as objectName");		
//		tableColMap.put("tbl_courses_sLiked tt right join tbl_courses t on tt.coursesId = t.ID","t.userId as receivedId ,t.coursesName as objectName");	
//		
//		tableColMap.put("tbl_sites","t.userId as receivedId ,t.title as objectName");
//		tableColMap.put("tbl_sites_share tt right join tbl_sites t on tt.siteID = t.id","t.userId as receivedId ,t.title as objectName");		
//		tableColMap.put("tbl_sites_isliked tt right join tbl_sites t on tt.commId = t.id","t.userId as receivedId ,t.title as objectName");		
//		tableColMap.put("tbl_article","t.userId as receivedId ,title as objectName");
//		tableColMap.put("tbl_article_share tt right join tbl_article t on tt.articleID = t.id","t.userId as receivedId ,t.title as objectName");		
//		tableColMap.put("tbl_article_isLiked tt right join tbl_article t on tt.articleID = t.id","t.userId as receivedId ,t.title as objectName");		
//		tableColMap.put("tbl_docs","t.userId as receivedId ,title as objectName");
//		tableColMap.put("tbl_docs_comment tt right join tbl_docs t on tt.docId = t.ID","t.userId as receivedId ,t.title as objectName");
//		
//		tableColMap.put("tbl_books","t.createPerson as receivedId ,t.bookname as objectName");		
//		tableColMap.put("tbl_books_share tt right join tbl_books t on tt.bookID = t.ID","t.createPerson as receivedId ,t.bookname as objectName");
//		tableColMap.put("tbl_books_isLiked tt right join tbl_books t on tt.bookID = t.ID","t.createPerson as receivedId ,t.bookname as objectName");
		
	}	
	//senderid   receivedid  smstype 
	@Transactional
	@Override
	public int addNotice(User user, String contextPath, String tableName,Integer topObjId,Integer objCreateperson,Integer smsType,Integer relationid,String ObjectName) {
		int ret = 0;
		if(user.getUserid().intValue() == objCreateperson.intValue()){
			logger.info("**addNotice**对自己评论或者点赞：user.getUserid()=="+user.getUserid()+"  objCreateperson=="+objCreateperson);
			//对自己评论或者点赞
			return 1;
		}else{
			//消息过滤
			SmsFilter sf = new SmsFilter();
			//
			sf.setUserid(objCreateperson);
			//过滤的消息类型   1私信  0消息
			sf.setFilteritem(smsType.intValue()==1?1:0);
			sf = smsFilterService.getFilterLvAndFansIds(sf);
			if(sf == null){
				logger.info("允许所有人发消息          SmsFilter=="+JSON.toJSONString(sf));
				//允许所有人发消息
				ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName);
				return ret;
			}
			if(sf.getFilterlevel().intValue()== sf.getFILTERLEVELS()[2].intValue()){
				logger.info("禁止所有人发消息           SmsFilter=="+JSON.toJSONString(sf));
				//禁止所有人发消息
				return 1;
				
			}else if(sf.getFilterlevel().intValue()== sf.getFILTERLEVELS()[1].intValue()){
				//只允许我关注的人发消息
				if(!StringUtils.isBlank(sf.getFansIds())){
					String []fansIds = sf.getFansIds().split(",");
					for (String ids : fansIds) {
						if(ids.equals(String.valueOf(user.getUserid()))){
							logger.info("只允许我关注的人发消息           SmsFilter=="+JSON.toJSONString(sf));
							logger.info("只允许我关注的人发消息           fansIds=="+JSON.toJSONString(fansIds));
							logger.info("当前用户是我关注的人, 可以发送信息           ids=="+ids +"    user.getUserid()=="+user.getUserid());
							//当前用户是我关注的人, 可以发送信息
							ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName);
							return ret;
						}else{
							logger.info("当前用户并不是是我关注的人, 不能发送私信          ids==" + ids +"    user.getUserid()=="+user.getUserid());
							return 1;
						}
					}				
				}else{
					logger.info("只允许我关注的人发消息           但是当前用户并没有关注任何人");
					//未关注任何人
					return 1;
				}
			}else{
				logger.info("允许所有人发消息          SmsFilter=="+JSON.toJSONString(sf));
				//允许所有人发消息
				ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName);			
			}
			return ret;
		}
	}
	//内部方法  发送通知
	private int sendSms(User user, String contextPath, String tableName,Integer topObjId,Integer objCreateperson,Integer smsType,Integer relationid,String ObjectName){
		//新增消息通知 
				Sms sms = new Sms();
				//设置消息发送者    即当前用户
				sms.setSenderid(user.getUserid());
				//消息接收人   被评论人或者对象主体创建人
				//如果被评论人不存在,则赋值为创建人
				sms.setReceivedid(objCreateperson);
				//设置消息类型
				sms.setSmstype(smsType);
				//设置消息主体ID
				sms.setRelationid(relationid);
				//设置对象主体名称
				sms.setObjectName(ObjectName);
				//设置发送消息人名字
				sms.setSendUserName(user.getUsername());
				
				logger.info("****添加消息通知：Sms=="+JSON.toJSONString(sms));
				logger.info("****添加消息通知：contextPath=="+contextPath+"***tableName=="+tableName);
				//提示语
				String tip = map.get(sms.getSmstype().toString());
				//发送者url    sms.getSendUserName() + 
				String userUrl = "<a href='"+ contextPath+"/user/mycenter?userid="+sms.getSenderid()+"'>" + "</a>";
				//对象url
				String objUrl = "<a href='"+contextPath+urlMap.get(tableName) +topObjId+ "'>" + sms.getObjectName() + "</a>";
				String content ="";
				if(tableName.equals("tbl_user")){
					//拼接消息内容
					content = userUrl + tip;
				}else{
					//拼接消息内容
					content = userUrl + tip + objUrl;
				}				
				sms.setSmscontent(content);
//				sms.setObjectName(objName);
				logger.info("****添加消息通知：入库     Sms=="+JSON.toJSONString(sms));
				//插入消息
				int ret = this.insert(sms);	
				//推送到接收人
				WebSocketUtil.send(objCreateperson.toString(), smsType.toString());
				return ret;
	}
	
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return smsDao.deleteByPrimaryKey(id);
	}

	//插入私信
	@Transactional
	@Override
	public int insertPrvivatSms(Sms record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sms");
		record.setId(id);
		
		// 消息过滤
		SmsFilter sf = new SmsFilter();
		//
		sf.setUserid(record.getReceivedid());
		// 过滤的消息类型 1私信
		sf.setFilteritem(record.getSMSTYPES()[1]);
		sf = smsFilterService.getFilterLvAndFansIds(sf);
		if (sf == null) {
			logger.info("允许所有人发私信         SmsFilter==" + JSON.toJSONString(sf));
			// 允许所有人发私信
			ret = this.insertSmsAndNotice(record);//smsDao.insert(record);
			return ret;
		}
		if (sf.getFilterlevel().intValue() == sf.getFILTERLEVELS()[2].intValue()) {
			logger.info("禁止所有人发私信           SmsFilter==" + JSON.toJSONString(sf));
			// 禁止所有人发私信
			return 1;

		} else if (sf.getFilterlevel().intValue() == sf.getFILTERLEVELS()[1].intValue()) {
			// 只允许我关注的人发私信
			if (!StringUtils.isBlank(sf.getFansIds())) {
				//查询粉丝ids
				String[] fansIds = sf.getFansIds().split(",");
				for (String ids : fansIds) {
					if (ids.equals(String.valueOf(record.getSenderid()))) {
						logger.info("只允许我关注的人发私信           SmsFilter==" + JSON.toJSONString(sf));
						logger.info("只允许我关注的人发私信          fansIds==" + JSON.toJSONString(fansIds));
						logger.info("当前用户是我关注的人, 可以发送私信          ids==" + ids +"  record.getSenderid()=="+record.getSenderid());
						// 当前用户是我关注的人, 可以发送私信
						ret = this.insertSmsAndNotice(record);//smsDao.insert(record);
						return ret;
					}else{
						logger.info("当前用户并不是是我关注的人, 不能发送私信          ids==" + ids +"  record.getSenderid()=="+record.getSenderid());
						return 1;
					}
				}
			} else {
				logger.info("只允许我关注的人发私信           但是当前用户并没有关注任何人");
				// 未关注任何人
				return 1;
			}
		} else {
			logger.info("允许所有人发私信       SmsFilter==" + JSON.toJSONString(sf));
			// 允许所有人发私信
			ret = this.insertSmsAndNotice(record);//smsDao.insert(record);
		}
		return ret;
	}
	@Transactional
	@Override
	public int insertSelective(Sms record) {
		
		return smsDao.insertSelective(record);
	}
	
	@Override
	public Sms selectByPrimaryKey(Integer id) {
		
		return smsDao.selectByPrimaryKey(id);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Sms record) {
		
		return smsDao.updateByPrimaryKeySelective(record);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeyWithBLOBs(Sms record) {
		
		return smsDao.updateByPrimaryKeyWithBLOBs(record);
	}

	/**
     * 获取所有消息
     * @param record
     * @return
     */
	@Override
	public Page<Sms> getAllSms(Sms record) {
		Page<Sms> page = new Page<Sms>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<Sms> list = smsDao.getAllSms(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
			for (Sms bookShare : list) {// 设置时间用于页面显示
				bookShare.setSenOrReTime(DateUtils.formatDate(bookShare.getSendtime(), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		return page;
	}
//	未读消息总数
	@Override
	public int getNewSmsSum(Integer userid) {
		
		return smsDao.getNewSmsSum(userid);
	}
	@Override
	public int getPrivateSmsSum(Integer userid) {
		
		return smsDao.getPrivateSmsSum(userid);
	}
	//我的消息总数 (未读、已读  消息&&私信)
	@Override
	public int geSmsSum(Integer userid) {
		
		return smsDao.geSmsSum(userid);
	}
	//弹窗显示未读消息列表
	@Override
	public List<Sms> getNewSms(Sms record) {
		int userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		record.setReceivedid(userid);
		List<Sms> list = smsDao.getNewSms(record);
		for (Sms sms : list) {// 设置时间用于页面显示
			sms.setSenOrReTime(DateUtils.formatDate(sms.getSendtime(), "yyyy-MM-dd HH:mm:ss"));
		}
		return list;
	}

	//阅读私信   更改阅读状态为已读
	@Transactional
	@Override
	public Sms getSmsDetail(Sms record) {
		if(record.getIslook() == 0 || record.getIslook()==null){
			//未读消息  标记为已读
			int ret = this.makeReadSms(record);
			if(ret>0){
//				record = this.selectByPrimaryKey(record.getId());
				return record;
			}else{
				return null;
			}
		}else{
			return null;
		}		
		
	}
	//	批量标记为已读                  传递ID即为单个,否则全部
	@Transactional
	@Override
	public int makeReadSms(Sms record) {
		return smsDao.makeReadSms(record);
	}
	//批量删除               传递ID即为单个,否则全部
	@Transactional
	@Override
	public int delSms(Sms record) {
		return smsDao.delSms(record);
	}
	@Transactional
	@Override
	public int insert(Sms record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_sms");
		record.setId(id);
		ret = smsDao.insert(record);
		return ret;
	}
	/**
     * 发送私信并且推送通知给对方
     * @param record
     * @return
     */
	@Override
	public int insertSmsAndNotice(Sms record) {
		int ret = 0;
		ret = smsDao.insert(record);
		//推送到接收人
		WebSocketUtil.send(record.getReceivedid().toString(), record.getSmstype().toString());
		return ret;
	}


}
