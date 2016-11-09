package com.jobplus.service.impl;

import com.jobplus.dao.AccountMapper;
import com.jobplus.dao.OperationSumMapper;
import com.jobplus.dao.UserMapper;
import com.jobplus.pojo.*;
import com.jobplus.service.*;
import com.jobplus.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("userService")
public class UserServiceImpl implements IUserService {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	/*
	 * (non-Javauser)
	 * 
	 * @see com.jobplus.service.IUserService#checkAccount(java.lang.String,
	 * java.lang.String)
	 */
	@Resource
	private UserMapper userDao;
	@Resource
	private AccountMapper accountDao;
	@Resource
	private OperationSumMapper operationSumMapperDao;
	@Resource
	private DBUtilsTemplate dBUtilsTemplate;

	@Resource
	private ISequenceService seqService;
	@Resource
	private FTPClientTemplate ftpClientTemplate;
	
	@Resource
	private IAttentionService attentionService;
	@Resource
	private ISmsService smsService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IAccountService accountService;

	@Override
	public User get(Integer userid) {
		
		return userDao.selectByPrimaryKey(userid);
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * 
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据 用户名/手机号/邮箱 查找用户
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findUserRoleByName(String userName) {

		return dBUtilsTemplate.find("select * From v_user_role_resource where userid =?", new Object[] { userName });

	}

	@Override
	public User findUserByName(String userName) {

		return dBUtilsTemplate.findFirst(User.class,
				"select * From tbl_user where userid = ? or mobile = ? or email=? ",
				new Object[] { userName, userName, userName });

	}

	@Override
	@Transactional
	public User post(User user) {

		// 获取当前时间
		java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());

		int userid = seqService.getSeqByTableName("tbl_user");
		// 设置用户ID
		user.setUserid(userid);
		String enCodePas = user.getPasswd();
		try {
			enCodePas = SHAUtil.shaEncode(enCodePas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPasswd(enCodePas);
		// 设置用户创建时间
		user.setCreatetime(time);
		user.setUpdatetime(time);
		// 判断是否为邮箱注册
		if (user.getEmail().indexOf("@") == -1) {
			user.setMobile(user.getEmail());
			user.setEmail("");
			user.setIsvalid(1);
		} else {
			// 后期邮箱注册默认为未激活状态，发送激活邮件激活账号
			user.setIsvalid(1);
		}

		// 入库
		if (userDao.insert(user) == 1) {
			// 初始化账户信息
			Account record = new Account();
			record.setId(seqService.getSeqByTableName("tbl_account"));
			record.setUserid(userid);
			record.setFreezeup(0);
			record.setJiamoney(0);
			record.setPoints(0);
			record.setCreatetime(time);
			accountDao.insert(record);

			// 初始化用户操作统计表
			OperationSum opSum = new OperationSum();
			opSum.setUserid(userid);
			opSum.setOperatortime(time);
			operationSumMapperDao.insert(opSum);
		}

		//发送短信
		smsService.sendSysNotice(userid,ConstantManager.FIRST_LOGIN_SMS);

		// 自动登录
		if (user.getIsvalid() == 1) {
			String username = String.valueOf(user.getUserid());
			String password = user.getPasswd();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			// token.setRememberMe(true);
			// 获取当前的Subject
			// 使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute("userid", username);
				currentUser.getSession().setAttribute("user", user);
				logger.info("用户[" + username + " sessionID:" + currentUser.getSession().getId() + "]登录认证通过");
				// 系统参数初始化操作
			} else {
				token.clear();
			}
		}

		// 存入reids

		// 建立lucene索引

		return user;
	}

	@Override
	@Transactional
	public User put(User record, HttpServletRequest request) {

		if (record.getUserid() != null) {
			// 判断用户只能更新自己的用户信息
			if (SecurityUtils.getSubject().getSession().getAttribute("userid").equals(record.getUserid())) {

				userDao.updateByPrimaryKeySelective(record);

			}
		}
		return record;
	}

	@Override
	@Transactional
	public int delete(Integer userid) {
		
		return userDao.deleteByPrimaryKey(userid);
	}

	@Override
	public int checkAccount(String checkName, String checkValue) {
		int check = 0;
		// 1 是用户名 2是手机 3是邮箱
		if ("1".equals(checkName)) {
			checkName = "userName";
		}
		if ("2".equals(checkName)) {
			checkName = "mobile";
		}
		if ("3".equals(checkName)) {
			checkName = "email";
		}
		check = userDao.checkAccount(checkName, checkValue);
		if (check > 0) {
			check = 1;
		}
		return check;
	}

	/**
	 * 通过用户Id获取用户简单信息 包括用户统计
	 * 
	 * @param userid
	 * @return
	 */
	@Override
	public User getUserSimpleInformation(Integer userid) {
		User user = userDao.getUserSimpleInformation(userid);
		String fansIds = (String)this.findFandIds(String.valueOf(userid)).get(0).get("fansIds");
		user.setFansIds(fansIds);		
		return user;
	}

	@Override
	public List<User> getFansListInformation(String collType, Integer objectId) {
		
		return userDao.getFansListInformation(collType, objectId);
	}
	
	@Override
	public List<Map<String,Object>> findFandIds(String userId) {

		return dBUtilsTemplate.find("select group_concat(userid) as fansIds  from tbl_attention where objectid=? and colltype='tbl_user'", new Object[] { userId });

	}

	/**
	 *  获取某本书籍的收藏者
	 * @param collectType   COLLECTTYPE={"tbl_users","tbl_topics","tbl_books"}
	 * @param actionType    收藏还是下载      （ 动作类型枚举   0下载 1收藏）ACTIONTYPE ={0,1}
	 * @param bookId    书籍ID
	 * @return
	 */
	@Override
	public List<User> getCollectUsers(String collectType,Integer actionType,Integer bookId) {
		return dBUtilsTemplate.find(User.class,"select DISTINCT u.userId,u.userName,u.headIcon ,u.description from tbl_collect cl ,tbl_user u where cl.collectType = ? and cl.actionType = ? and cl.objectId = ? and cl.userId = u.userId ", new Object[] {collectType,actionType,bookId});
	}
	 /**
     * 个人中心：我的粉丝列表
     * @param User record
     * @return
     */
	@Override
	public Page<User> getMyFansList(User record) {
		Page<User> page = new Page<User>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		
		int count = userDao.getMyFansListCount(record);
		if(count < 1)
			return page;
		List<User> list = userDao.getMyFansList(record);
		if (list.size() > 0) {
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
			// for (User User : list) {// 设置时间用于页面显示
			// User.setUserShareTime(DateUtils.formatDate(User.getCreatetime(),
			// "yyyy-MM-dd"));
			// }
		}
		return page;
	}
	 /**
     * 个人中心：我关注的人列表
     * @param User record
     * @return
     */
	@Override
	public Page<User> getAttenManList(User record) {
		Page<User> page = new Page<User>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = userDao.getAttenManListCount(record);
		if(count < 1)
			return page;
		List<User> list = userDao.getAttenManList(record);
		if (list.size() > 0) {
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int updUserInfoIncHeadIcon(MultipartFile files, User user, HttpServletRequest request,
			HttpServletResponse response) {
		// 定义上传路径
		String path = "";
		String headicon = "";
		if (files.getOriginalFilename() != null) {
			// 重命名上传后的文件名
			String myFileName = files.getOriginalFilename();
			// 文件类型
			String usersuffix = myFileName.substring(myFileName.lastIndexOf(".") + 1);
			// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
			if (myFileName.trim() != "") {
				// 重命名上传后的文件名   用户id_ 时间戳+文件类型
				String fileName = user.getUserid()+"_"+UUIDGenerator.getUUID()+ "."+usersuffix;
				// dir.server=http://192.168.0.39:8199/
				// 定义上传路径
				path =  ftpClientTemplate.ftpImgDir + "/" + ftpClientTemplate.ftpHeadIconDir  +"/"+DateUtils.getDateTime2()+"/"+  fileName;
				headicon = ftpClientTemplate.headIconServer +ftpClientTemplate.ftpHeadIconDir +"/"+DateUtils.getDateTime2()+"/"+ fileName;
				// 设置头像路径
				user.setHeadicon(headicon);
				// 保存到本地
				// File localFile = new File(path);
				// files.transferTo(localFile);

			}
		}
		int ret = 0;			
		try {
				//File_Exits(0), Create_Directory_Success(1), Create_Directory_Fail(2), Upload_From_Break_Success(3), Upload_From_Break_Faild(4), 
				//Download_From_Break_Success(5), Download_From_Break_Faild(6), Upload_New_File_Success(7), Upload_New_File_Failed(8), 
				//Delete_Remote_Success(9),Delete_Remote_Faild(10),Remote_Bigger_Local(11),Remote_smaller_local(12),Not_Exist_File(13),
				//Remote_Rename_Success(14),Remote_Rename_Faild(15),File_Not_Unique(16);
				// 连接成功
				FTPStatus fst = ftpClientTemplate.upload(files.getInputStream(), path,true);
				//Upload_New_File_Success(7)
				if (fst.getStatus() == 7) {
					ret = 1;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// 文档文件上传到服务器成功    插入数据库
		if (ret > 0) {
			ret = this.updateByPrimaryKeySelective(user);
		}
		// 多文件  上传到服务器 暂时不用
//		try {
//			threadFTPUtils.uploadInit(path, files.getInputStream(), FTPType.UPLOAD);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Thread thread3 = new Thread(threadFTPUtils);
//		thread3.start();
		return ret;

	}
	
	/**
	 * 更新密码
	 *
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
    public int changePassword(User user) {
        User old = null;
        if (StringUtils.isNotBlank(user.getEmail())) {
            old = userDao.getAccount("email", user.getEmail());
        } else {
            old = userDao.getAccount("mobile", user.getMobile());
        }
        if (old != null) {
            String enCodePas = user.getPasswd();
            try {
                enCodePas = SHAUtil.shaEncode(enCodePas);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (enCodePas.equals(old.getPasswd()))
                return -1;
            old.setPasswd(enCodePas);
            old.setUpdatetime(new java.sql.Timestamp(System.currentTimeMillis()));
            return userDao.updateByPrimaryKeySelective(old);
        }
        return 0;
    }

    /**
     * 更新用户操作统计数
     * @param request
     */
	@Override
	public void getMyHeadTopAndOper(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("userid");
//		//我关注的人总数
//		int attenManSum = attentionService.getAttenManSum(Integer.parseInt(userid));
//		//我的粉丝总数
//		int fansSum = attentionService.getFansSum(Integer.parseInt(userid));
		//未读消息总数(除私信)
//		int newSmsSum = smsService.getNewSmsSum(Integer.parseInt(userid));
//		//未读私信总数
//		int privateSmsSum = smsService.getPrivateSmsSum(Integer.parseInt(userid));
//		//我的消息总数 (未读、已读  消息&&私信)
//		int smsSum = smsService.geSmsSum(Integer.parseInt(userid));
		//我关注的人 ids 和我的粉丝  ids
		String attenMan = attentionService.getAttenMan(Integer.parseInt(userid));
		String fans = attentionService.getFans(Integer.parseInt(userid));

		// 获取统计数
		OperationSum operationSum = getOperationSum(request);
		@SuppressWarnings("unchecked")
		Map<Object, Object> myHeadTop = null==(Map<Object, Object>)request.getSession().getAttribute("myHeadTop")
												?new HashMap<Object, Object>():(Map<Object, Object>)request.getSession().getAttribute("myHeadTop");
		myHeadTop.put("attenManSum", operationSum.getAttentionsum());//		myHeadTop.put("attenManSum", attenManSum);
		myHeadTop.put("fansSum", operationSum.getFanssum());//		myHeadTop.put("fansSum", fansSum);
//		myHeadTop.put("newSmsSum", newSmsSum);
//		myHeadTop.put("privateSmsSum", privateSmsSum);
//		myHeadTop.put("smsSum", smsSum);
		myHeadTop.put("attenMan", attenMan);
		myHeadTop.put("fans", fans);
		//存入session  
		request.getSession().removeAttribute("operationSum");
		request.getSession().removeAttribute("myHeadTop");
		request.getSession().setAttribute("operationSum", operationSum);
		request.getSession().setAttribute("myHeadTop", myHeadTop);
		
		Account account = accountService.getAccountByUserId(Integer.parseInt(userid));
		request.getSession().removeAttribute("account");
		request.getSession().setAttribute("account", account);
//		logger.info("**个人账户信息放入session account=="+JSON.toJSONString(account));
	}
	/**
     * 更新用消息统计数
     * @param request
     */
	@Override
	public void getSmsOper(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("userid");
		//未读消息总数(除私信)
		int newSmsSum = smsService.getNewSmsSum(Integer.parseInt(userid));
		//未读私信总数
		int privateSmsSum = smsService.getPrivateSmsSum(Integer.parseInt(userid));
		//我的消息总数 (未读、已读  消息&&私信)
		int smsSum = smsService.geSmsSum(Integer.parseInt(userid));
		
		@SuppressWarnings("unchecked")
		Map<Object, Object> myHeadTop = null==(Map<Object, Object>)request.getSession().getAttribute("myHeadTop")
											?new HashMap<Object, Object>():(Map<Object, Object>)request.getSession().getAttribute("myHeadTop");
		myHeadTop.put("newSmsSum", newSmsSum);
		myHeadTop.put("privateSmsSum", privateSmsSum);
		myHeadTop.put("smsSum", smsSum);
		request.getSession().removeAttribute("myHeadTop");
		request.getSession().setAttribute("myHeadTop", myHeadTop);
	}
	/**
	 * 内部方法 获取用户操作统计
	 */
	@Override
	public OperationSum getOperationSum(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("userid");
		if (!StringUtils.isBlank(userid)) {
			OperationSum operationSum = new OperationSum();
			operationSum = operationSumService.selectByPrimaryKey(Integer.parseInt(userid));
//			logger.info("**getOperationSum**获取用户操作统计*****operationSum=="+JSON.toJSONString(operationSum));
			return operationSum;
		}
		return null;
	}

	/**
	 * 后台查询用户列表
	 *
	 * @param gridQuery
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAllUsers(GridQuery gridQuery) {
		User record = gridQuery.getCondition() == null ? new User() : (User) gridQuery.getCondition();
		List<User> list = null;
		int count = userDao.countList(record);
		if (count > 0) {
			record.setPageNo(gridQuery.getPage());
			record.setPageSize(gridQuery.getRows());
			record.setLimitSt(record.getPageNo() * record.getPageSize() - record.getPageSize());
			list = userDao.getList(record);
		}
		return GridDataUtil.getGridMap(gridQuery.getRows(), gridQuery.getPage(), count, list);
	}

	@Override
	public List<User> getRewardUsers(Integer articleId) {
		List<User> list = new ArrayList<User>();
		list = userDao.getRewardUsers(articleId);
		return list;
	}
	/**
     * 统计个人用户完整度
     *
     * @param userId
     * @return
     */
	@Override
	public int userInfoCompletion(int userId) {
		int complete = userDao.userInfoCompletion(userId);
		return complete;
	}
}
