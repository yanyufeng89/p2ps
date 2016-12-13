package com.jobplus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.dao.BusiAreaLibMapper;
import com.jobplus.dao.CompanyIntroMapper;
import com.jobplus.dao.MyHomePageMapper;
import com.jobplus.pojo.BusiAreaLib;
import com.jobplus.pojo.CompanyIntro;
import com.jobplus.pojo.CompanyNews;
import com.jobplus.pojo.FTPStatus;
import com.jobplus.pojo.MyHomePage;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.VisitHistory;
import com.jobplus.service.IAttentionService;
import com.jobplus.service.ICompanyIntroService;
import com.jobplus.service.ICompanyNewsService;
import com.jobplus.service.ICompanyService;
import com.jobplus.service.IEducationBgrdService;
import com.jobplus.service.IMyHomePageService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IPersonalSkillService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsFilterService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUserService;
import com.jobplus.service.IVisitHistoryService;
import com.jobplus.service.IWorkExperService;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.FTPClientTemplate;
import com.jobplus.utils.UUIDGenerator;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

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
	@Resource
	private ICompanyIntroService companyIntroService;
	@Resource
	private ICompanyNewsService companyNewsService;
	@Resource
	private FTPClientTemplate ftpClientTemplate;
	@Resource
	private BusiAreaLibMapper busiAreaLibDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private CompanyIntroMapper companyIntroDao;
	
	
	
	@Transactional
	@Override
	public String uploadImg(MultipartFile[] files, User user) {
		// 定义上传路径
		String path = "";
		String imgUrl = "";

		for (int i = 0; i < files.length; i++) {
			String tmpUrl = "";
			if (files[i].getOriginalFilename() != null) {
				// 重命名上传后的文件名
				String myFileName = files[i].getOriginalFilename();
				// 文件类型
				String usersuffix = myFileName.substring(myFileName.lastIndexOf(".") + 1);
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					// 重命名上传后的文件名 用户id_ 时间戳+文件类型
					String fileName = user.getUserid() + "_" + UUIDGenerator.getUUID() + "." + usersuffix;
					// 定义上传路径
					path = ftpClientTemplate.ftpImgDir + "/" + DateUtils.getDateTime2() + "/" + fileName;
					tmpUrl = ftpClientTemplate.headIconServer + DateUtils.getDateTime2() + "/" + fileName;
				}

				try {
					//File_Exits(0), Create_Directory_Success(1), Create_Directory_Fail(2), Upload_From_Break_Success(3), Upload_From_Break_Faild(4), 
					//Download_From_Break_Success(5), Download_From_Break_Faild(6), Upload_New_File_Success(7), Upload_New_File_Failed(8), 
					//Delete_Remote_Success(9),Delete_Remote_Faild(10),Remote_Bigger_Local(11),Remote_smaller_local(12),Not_Exist_File(13),
					//Remote_Rename_Success(14),Remote_Rename_Faild(15),File_Not_Unique(16);
					// 连接成功
					FTPStatus fst = ftpClientTemplate.upload(files[i].getInputStream(), path, true);
					// Upload_New_File_Success(7)
					if (fst.getStatus() == 7) {
						// 文件上传成功
						imgUrl += tmpUrl + ";";
					}
				} catch (Exception e) {
					System.out.println("图片上传失败");
					e.printStackTrace();
				}
			}

		}
		// 文档文件上传到服务器成功 返回url地址
		if (imgUrl.length() > 0) {
			return imgUrl;
		}
		return "";

	}
	
	
	
	
	/**
	 * 获取最近分享的6类
	 * 
	 * @param record
	 * @return
	 */
/*	@SuppressWarnings("static-access")
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
	}*/
	/**
	 * 获取我分享的某一类   tableName;tableColumn;userid;
	 * @param request
	 * @param record
	 * @return
	 */
/*	@SuppressWarnings("static-access")
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
				hp.setObjurl(request.getContextPath() + hp.getUrlMap().get(StringUtils.isBlank(record.getTableName())?hp.getType():record.getTableName())+ hp.getId());
				hp.setTableName(record.getTableName());
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}*/
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
		//公司信息
		CompanyIntro cpnInfo = companyIntroService.selectByPrimaryKey(Integer.parseInt(userid));
		
		if(null == userInfo){
			mv.setViewName("404");
			return mv;
		}

		//公司快讯
		CompanyNews cpNews = new CompanyNews();
		cpNews.setCompid(Integer.parseInt(userid));
		Page<CompanyNews> cpNewes = companyNewsService.getNewsList(cpNews);
		
		VisitHistory vh = new VisitHistory();
		vh.setUserid(Integer.parseInt(userid));
		// 访问我的主页类型
		vh.setVisittype(vh.getVISITTYPES()[0]);
		// 最近访问的人
		Page<VisitHistory> visitors = visitHistoryService.getRecentVistors(vh);

		//简历完成度
		int completion = this.cpInfoCompletion(Integer.parseInt(userid));

		//方便前端画图(计算)  头像和用户名权重占 0.06
		if (completion == 0)
			completion = 0;
		else if (completion <= 35)
			completion = 25;
		else if (completion <= 60)
			completion = 50;
		else if (completion <= 95)
			completion = 75;
		else
			completion = 100;
		
		
		
		// 获取当前url
		StringBuffer url = request.getRequestURL();
		/*if (request.getQueryString() != null) {
			url.append("?");
			url.append("userid="+userid);
//			url.append(request.getQueryString());
		}*/

		mv.addObject("visitors", visitors);
		mv.addObject("userInfo", userInfo);
		mv.addObject("cpnInfo", cpnInfo);
//		mv.addObject("workExList", workExList);
//		mv.addObject("eduList", eduList);
		mv.addObject("homePageUrl", url);
//		mv.addObject("personalSkill", personalSkill);
		mv.addObject("completion", completion);
		mv.addObject("cpNewes", cpNewes);
		
		// 教育背景和工作经历放入session中 '最近访问的人'页面需要
//		request.getSession().setAttribute("workExList", workExList);
//		request.getSession().setAttribute("eduList", eduList);

		// 如果是个人信息预览 isReview为1
		if (userid.equals(cutUserid) && "0".equals(isReview)) {
			/********** 本人 查看自己主页 ***/
			mv.setViewName("mydocs/mycenter/companyCenter");
		} else {
			/************** 查看别人的主页 *****/
			// 最新动态
			MyHomePage myhp = new MyHomePage();
			myhp.setUserid(Integer.parseInt(userid));
			Page<MyHomePage> recentShare = myHomePageService.getRecentShare(request, myhp);

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
			// 查看他人主页数据不准确 临时改动 FIXME  查看他人主页数据不准确 临时改动********************************
//			operationSum = operationSumService.selectByPrimaryKey(Integer.parseInt(userid));
			operationSum = myHomePageService.getRealSum(Integer.parseInt(userid));		
			// 查看他人主页数据不准确 临时改动 FIXME  查看他人主页数据不准确 临时改动********************************
			
			
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
			// 他人访问
			mv.setViewName("mydocs/mycenter/aboutCompany");

			if (!StringUtils.isBlank(cutUserid) && !userid.equals(cutUserid)) {
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
	
	/**
	 * 实时获取用户分享的总数（成功的）
	 */
	/*@Override
	public OperationSum getRealSum(Integer userid){
		OperationSum opts = new OperationSum();
		// 默认查看分享是文档
		MyHomePage home = new MyHomePage();
		int allShareCount = 0;
		
		//文档分享总数(成功的 非私有)
		home.setTableName("tbl_docs");
		home.setTableColumn("title ");
		home.setTableColumn2("userid ");
		home.setUserid(userid);
		int count = myHomePageDao.getOneSharesCount(home);
		opts.setDocsharesum(count);
		allShareCount =allShareCount + count;
		
		//话题分享总数
		home.setTableName("tbl_topics");
		home.setTableColumn("title ");
		home.setTableColumn2("createPerson ");
		home.setUserid(userid);
		count = myHomePageDao.getOneSharesCount(home);
		opts.setTopicssharesum(count);
		allShareCount =allShareCount + count;
		
		//书籍分享总数
		home.setTableName("tbl_books");
		home.setTableColumn("bookname ");
		home.setTableColumn2("userid ");
		home.setUserid(userid);
		count = myHomePageDao.getOneSharesJustToBooksCount(home);
		opts.setBooksharesum(count);
		allShareCount =allShareCount + count;
		
		//文章分享总数
		home.setTableName("tbl_article");
		home.setTableColumn("title ");
		home.setTableColumn2("userid ");
		home.setUserid(userid);
		count = myHomePageDao.getOneSharesCount(home);
		opts.setArticlesharesum(count);
		allShareCount =allShareCount + count;
		
		//课程分享总数
		home.setTableName("tbl_courses");
		home.setTableColumn("coursesName ");
		home.setTableColumn2("userid ");
		home.setUserid(userid);
		count = myHomePageDao.getOneSharesCount(home);
		opts.setCoursessharesum(count);
		allShareCount =allShareCount + count;
		
		//站点分享总数
		home.setTableName("tbl_sites");
		home.setTableColumn("title ");
		home.setTableColumn2("userid ");
		home.setUserid(userid);
		count = myHomePageDao.getOneSharesCount(home);
		opts.setSitessharesum(count);
		allShareCount =allShareCount + count;
		
		opts.setAllshresum(allShareCount);
		return opts;
	}*/
	

//插入业务领域
	@Override
	public int insertBusLib(BusiAreaLib record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_busiarealib");
		record.setId(id);
		record.setCreatetime(new Date());
		record.setUpdatetime(new Date());
		record.setIsvalid(1);
		ret = busiAreaLibDao.insert(record);
		return ret;
	}




	@Override
	public int cpInfoCompletion(Integer id) {
		return companyIntroDao.cpInfoCompletion(id);
	}

}
