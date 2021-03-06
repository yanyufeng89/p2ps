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
	
	/**消息类型   0系统消息    1私信  2关注通知(成为您的粉丝)                  2
	*11话题新增回答  12话题的回答新增评论     13话题新增评论   14话题评论新增回复      15话题回答点赞     7
	*21书籍推荐新增回复   22书籍点赞										9
	*31课程新增推荐  32课程新增评论 	 33课程点赞							12
	*41文章新增推荐   42文章新增评论 	 43文章点赞							15
	*51站点新增推荐   52站点新增评论 	 53站点点赞							18
	*61文档新增推荐   62文档新增评论	 63文档点赞							21
	*3关注了您发布的话题  
    * */
	private static Map<String, String> map = new HashMap<String,String>();
	public static Map<String, String> urlMap = new HashMap<String,String>();
//	private static Map<String, String> tableColMap = new HashMap<String,String>();
	static {
		map.put("2","关注了您");
		map.put("11","回答了您分享的话题");
		map.put("12","回复了您话题的评论");
		map.put("13","评论了您的话题");
		map.put("14","回复了您的话题");
		map.put("15","赞了您的话题");
		map.put("21","回复了您分享的书籍");
		map.put("22","赞了您分享的书籍");
		map.put("31","推荐了您分享的课程");
		map.put("32","评论了您分享的课程");
		map.put("33","赞了您分享的课程");
		map.put("41","评论了您分享的文章");
		map.put("42","评论了您分享的文章");
		map.put("43","赞了您分享的文章");
		map.put("51","评论了您分享的站点");
		map.put("52","评论了您分享的站点");
		map.put("53","赞了您分享的站点");
		map.put("61","评论了您分享的文档");
		map.put("62","评论了您分享的文档");
		map.put("63","赞了您分享的文档");
		map.put("3","关注了您发布的话题");
		map.put("80","邀请您回答");
		map.put("90","下载了您的文档");
		map.put("100","打赏了您的文章");
		map.put("110","采纳您的回答");
		
		//企业
		map.put("1000","评论了您的主页快讯");
		map.put("1001","赞了您的主页快讯");

		urlMap.put("tbl_user","");
		urlMap.put("tbl_topics","/topics/getTopicsDetail/");
		urlMap.put("tbl_topics_comment","/topics/getTopicsDetail/");		
		urlMap.put("tbl_topics_isLiked","/topics/getTopicsDetail/");		
		urlMap.put("tbl_courses","/courses/getCourseDetail/");
		urlMap.put("tbl_courses_share","/courses/getCourseDetail/");		
		urlMap.put("tbl_courses_Liked","/courses/getCourseDetail/");		
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
		
		
	}	
	//senderid   receivedid  smstype 
	@Transactional
	@Override
	public int addNotice(User user, String contextPath, String tableName,Integer topObjId,Integer objCreateperson,Integer smsType,Integer relationid,String ObjectName,String content) {
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
				ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName,content);
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
							ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName,content);
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
				ret = this.sendSms(user, contextPath, tableName, topObjId, objCreateperson, smsType, relationid, ObjectName,content);			
			}
			return ret;
		}
	}
	//内部方法  发送通知
	private int sendSms(User user, String contextPath, String tableName,Integer topObjId,Integer objCreateperson,Integer smsType,Integer relationid,String ObjectName,String content){
		Sms sms = new Sms();
		if(0 == smsType){//系统通知
			//设置消息发送者     系统通知
			sms.setSenderid(0);
			//消息接收人   被评论人或者对象主体创建人
			//如果被评论人不存在,则赋值为创建人
			sms.setReceivedid(objCreateperson);
			//设置消息类型
			sms.setSmstype(smsType);
//			//设置消息主体ID
//			sms.setRelationid(relationid);
//			//设置对象主体名称
//			sms.setObjectName(ObjectName);
//			//设置发送消息人名字
//			sms.setSendUserName(user.getUsername());
			sms.setSmscontent(content);
			
		}else{
			//新增消息通知 
			
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
			//发送者url    sms.getSendUserName() +    暂时不需要 
			String userUrl = "";//"<a target='_blank' href='"+ contextPath+"/user/mycenter?userid="+sms.getSenderid()+"'>" + "</a>";
			String objName = StringUtils.isBlank(sms.getObjectName())?"":sms.getObjectName();
			//对象url
			String objUrl = "<a target='_blank' href='"+contextPath+urlMap.get(tableName) +topObjId+ "'>" + 
					objName + "</a>";
//			String content ="";
			if(tableName.equals("tbl_user")){
				//拼接消息内容
				content = userUrl + tip + content;
			}else{
				//拼接消息内容
				content = userUrl + tip + objUrl  + content;
			}				
			sms.setSmscontent(content);
		}	
		
		// sms.setObjectName(objName);
		logger.info("****添加消息通知：入库     Sms==" + JSON.toJSONString(sms));
		// 插入消息
		int ret = this.insert(sms);
		// 推送到接收人
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
		sf.setFilteritem(1);
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
					}
				}
					logger.info("当前用户并不是是我关注的人, 不能发送私信          ids==" + fansIds +"  record.getSenderid()=="+record.getSenderid());
					return 1;
				
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
		//此处修改为每页8条  page.pageSize 相应的改变     原:page.getPageSize()
		record.setPageSize(8);
		page.setPageSize(8);
		
		record.setLimitSt(record.getPageNo() * 8 - 8);
		int count = smsDao.getAllSmsCount(record);
		if(count < 1)
			return page;
		List<Sms> list = smsDao.getAllSms(record);
		if (list.size() > 0) {
			page.initialize((long)count, record.getPageNo());
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
		//避免多次 关注同一人
		if("关注了您".equals(record.getSmscontent()))
			ret = smsDao.insertOrUpdateSms(record);
		else{
			ret = smsDao.insert(record);
		}		
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
	/**
     * 发送系统通知
     */
	@Override
	public int sendSysNotice(Integer userid, String content) {
		int ret = this.sendSms(null, "", "", null, userid, 0, null, "", content);
		return ret;
	}
/*	// FIXME  待完善
	@Override
	public int addNotices(User user, String userUrl, String content, String objUrl, String content2) {
		// TODO Auto-generated method stub
		return 0;
	}*/


}
