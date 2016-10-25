package com.jobplus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.dao.MyHomePageMapper;
import com.jobplus.pojo.EducationBgrd;
import com.jobplus.pojo.MyHomePage;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.PersonalSkill;
import com.jobplus.pojo.User;
import com.jobplus.pojo.VisitHistory;
import com.jobplus.pojo.WorkExper;
import com.jobplus.service.IAttentionService;
import com.jobplus.service.IEducationBgrdService;
import com.jobplus.service.IMyHomePageService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IPersonalSkillService;
import com.jobplus.service.ISmsFilterService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUserService;
import com.jobplus.service.IVisitHistoryService;
import com.jobplus.service.IWorkExperService;

@Service("myHomePageService")
public class MyHomePageServiceImpl implements IMyHomePageService {

	@Resource
	private MyHomePageMapper myHomePageDao;
	@Resource
	private ISmsService smsService;
	@Resource
	private ISmsFilterService smsFilterService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IVisitHistoryService visitHistoryService;
	@Resource
	private IWorkExperService workExperService;
	@Resource
	private IEducationBgrdService educationBgrdService;
	@Resource
	private IUserService userService;
	@Resource
	private IMyHomePageService myHomePageService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IPersonalSkillService personalSkillService;

	/**
	 * 获取最近分享的6类
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public Page<MyHomePage> getRecentShare(HttpServletRequest request,MyHomePage record) {

		Page<MyHomePage> page = new Page<MyHomePage>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = myHomePageDao.getRecentShareCount(record);
		if(count < 1)
			return page;
		List<MyHomePage> list = myHomePageDao.getRecentShare(record);
		if (list.size() > 0) {
			for (MyHomePage hp : list) {
			//拼接URl
				hp.setObjurl(request.getContextPath() + hp.getUrlMap().get(hp.getType())+ hp.getId());
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}
	/**
	 * 获取我分享的某一类   tableName;tableColumn;userid;
	 * @param request
	 * @param record
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Override
	public Page<MyHomePage> getOneShares(HttpServletRequest request, MyHomePage record) {
		Page<MyHomePage> page = new Page<MyHomePage>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		
		List<MyHomePage> list = null;
		int count = 0;
		//最近分享   所有分类
		if(StringUtils.isBlank(record.getTableName())){
			count = myHomePageDao.getRecentShareCount(record);
			if(count < 1)
				list = new ArrayList<MyHomePage>();
			else
				list = myHomePageDao.getRecentShare(record);
		}else if("tbl_books".equals(record.getTableName())){ //最近分享的书籍 特殊处理
			count = myHomePageDao.getOneSharesJustToBooksCount(record);
			if(count < 1)
				list = new ArrayList<MyHomePage>();
			else
			 list = myHomePageDao.getOneSharesJustToBooks(record);
		}else{//其他的某一类分享
			count = myHomePageDao.getOneSharesCount(record);
			if(count < 1)
				list = new ArrayList<MyHomePage>();
			else
			list = myHomePageDao.getOneShares(record);
		}
		if (list.size() > 0) {
			for (MyHomePage hp : list) {			
				hp.setObjurl(request.getContextPath() + hp.getUrlMap().get(record.getTableName())+ hp.getId());
				hp.setTableName(record.getTableName());
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}
	/**
	 *  个人主页  && 浏览他人主页
	 * @param mv
	 * @param cutUserid
	 * @return
	 */
	@Override
	public ModelAndView getHomePage(HttpServletRequest request, ModelAndView mv, String userid, String cutUserid,
			String isReview) {
		// 用户信息
		User userInfo = userService.get(Integer.parseInt(userid));
		
		if(null == userInfo){
			mv.setViewName("404");
			return mv;
		}

		VisitHistory vh = new VisitHistory();
		vh.setUserid(Integer.parseInt(userid));
		// 访问我的主页类型
		vh.setVisittype(vh.getVISITTYPES()[0]);
		// 最近访问的人
		Page<VisitHistory> visitors = visitHistoryService.getRecentVistors(vh);

		// 工作经验列表
		WorkExper we = new WorkExper();
		we.setUserid(Integer.parseInt(userid));
		List<WorkExper> workExList = workExperService.getExperList(we);
		// 教育背景列表
		EducationBgrd edb = new EducationBgrd();
		edb.setUserid(Integer.parseInt(userid));
		List<EducationBgrd> eduList = educationBgrdService.getExperList(edb);
		// 个人技能
		PersonalSkill personalSkill = new  PersonalSkill();
		personalSkill.setUserid(Integer.parseInt(userid));
		personalSkill = personalSkillService.selectByRecord(personalSkill);
		//简历完成度
		int completion = userService.userInfoCompletion(Integer.parseInt(userid));
		
		
		// 获取当前url
		StringBuffer url = request.getRequestURL();
		if (request.getQueryString() != null) {
			url.append("?");
			url.append("userid="+userid);
//			url.append(request.getQueryString());
		}

		mv.addObject("visitors", visitors);
		mv.addObject("userInfo", userInfo);
		mv.addObject("workExList", workExList);
		mv.addObject("eduList", eduList);
		mv.addObject("homePageUrl", url);
		mv.addObject("personalSkill", personalSkill);
		mv.addObject("completion", completion);
		// 教育背景和工作经历放入session中 '最近访问的人'页面需要
		request.getSession().setAttribute("workExList", workExList);
		request.getSession().setAttribute("eduList", eduList);

		// 如果是个人信息预览 isReview为1
		if (userid.equals(cutUserid) && StringUtils.isBlank(isReview)) {
			/********** 本人 查看自己主页 ***/
			mv.setViewName("mydocs/mycenter/mycenter");
		} else {
			/************** 查看别人的主页 *****/
			// 最新动态
			MyHomePage myhp = new MyHomePage();
			myhp.setUserid(Integer.parseInt(userid));
			Page<MyHomePage> recentShare = this.getRecentShare(request, myhp);

			// 页面端如何判断是否是当前登录人
			// 如果是不需要获取atdAndFans; 直接从session中获取

			// 我关注的人总数
			int attenManSum = attentionService.getAttenManSum(Integer.parseInt(userid));
			// 我的粉丝总数
			int fansSum = attentionService.getFansSum(Integer.parseInt(userid));
			// 我的消息总数 (未读、已读 消息&&私信)
			int smsSum = smsService.geSmsSum(Integer.parseInt(userid));
			// 我关注的人 ids 和我的粉丝 ids
			String attenMan = attentionService.getAttenMan(Integer.parseInt(userid));
			String fans = attentionService.getFans(Integer.parseInt(userid));
			OperationSum operationSum = new OperationSum();
			operationSum = operationSumService.selectByPrimaryKey(Integer.parseInt(userid));
			// 我的关注和我的粉丝
			Map<Object, Object> atdAndFans = new HashMap<>();
			atdAndFans.put("attenManSum", attenManSum);
			atdAndFans.put("fansSum", fansSum);
			atdAndFans.put("smsSum", smsSum);
			atdAndFans.put("attenMan", attenMan);
			atdAndFans.put("fans", fans);
			atdAndFans.put("operationSum", operationSum);

			// 默认查看分享是文档
			MyHomePage home = new MyHomePage();
			home.setTableName("tbl_docs");
			home.setTableColumn("title ");
			home.setTableColumn2("userid ");
			home.setUserid(Integer.parseInt(userid));
			Page<MyHomePage> shares = myHomePageService.getOneShares(request, home);

			mv.addObject("recentShare", recentShare);
			mv.addObject("atdAndFans", atdAndFans);
			mv.addObject("shares", shares);
			// logger.info("getOneShares 获取我分享的某一类 recentShare:" +
			// JSON.toJSONString(shares));
			//
			// logger.info("getHomePage 个人主页 recentShare:" +
			// JSON.toJSONString(recentShare));
			// logger.info("getHomePage 个人主页 atdAndFans:" +
			// JSON.toJSONString(atdAndFans));
			// 他人访问
			mv.setViewName("mydocs/mycenter/aboutme");

			if (!StringUtils.isBlank(cutUserid)) {
				// 增加访问记录
				VisitHistory vhy = new VisitHistory();
				vhy.setVisitorid(Integer.parseInt(cutUserid));
				vhy.setUserid(Integer.parseInt(userid));
				vhy.setVisittype(vhy.getVISITTYPES()[0]);

				vhy.setVisiturl(url.toString());
				visitHistoryService.insertOrUpdate(vhy);
			}
		}
		return mv;
	}

}
