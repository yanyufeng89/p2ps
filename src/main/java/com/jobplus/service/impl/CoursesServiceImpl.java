package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.CoursesMapper;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.Courses;
import com.jobplus.pojo.CoursesShare;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.service.IAccountService;
import com.jobplus.service.ICoursesService;
import com.jobplus.service.ICoursesShareService;
import com.jobplus.service.IMyCollectService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.SolrJUtils;

@Service("coursesService")
public class CoursesServiceImpl implements ICoursesService{

	@Resource
	private CoursesMapper coursesDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUserService userService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private ICoursesShareService coursesShareService;
	@Resource
	private IMyCollectService myCollectService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private IAccountService accountService;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return 0;
	}

	@Transactional
	@Override
	public int insert(Courses record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_courses");
		record.setId(id);
		ret = coursesDao.insert(record);
		if(ret > 0){
			//operationSum表  课程分享数增加
			ret = operationSumService.updOperationSum(10, 0, 1,null);
			//增加财富值
			accountService.modAccountAndDetail(record.getUserid(), 0, new Account().getSCORES()[0], 
					1, 0, new Account().getSCORES()[0],3);
		}
		return ret;
	}
	//同时 插入一条书籍分享记录
	@Transactional
	@Override
	public int insertCouseAndCourseShare(Courses record) {
		CoursesShare share = new CoursesShare();
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_courses");
		int shareId = seqService.getSeqByTableName("tbl_courses_share");		
		record.setId(id);
		share.setId(shareId);
		share.setUserid(record.getUserid());
		share.setCoursesid(id);
		share.setRecommend(record.getRecommend());		
		
		ret = coursesDao.insert(record);
		if(ret > 0){
			//operationSum表  课程分享数增加
			ret = operationSumService.updOperationSum(10, 0, 1,null);
			//同时 插入一条书籍分享记录
			ret = coursesShareService.insert(share);
			// 初始默认值+1     对应课程的评论数 + 1
			updTableColumnService.updNums(3, 1, 0, 1, id);
			
			//增加财富值
			accountService.modAccountAndDetail(record.getUserid(), 0, new Account().getSCORES()[0], 
					1, 0, new Account().getSCORES()[0],3);
		}
		return ret;
	}

	@Override
	public int insertSelective(Courses record) {
		
		return coursesDao.insertSelective(record);
	}

	@Override
	public Courses selectByPrimaryKey(Integer id) {
		
		return coursesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Courses record) {
		
		return coursesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Courses record) {
		
		return coursesDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Courses record) {
		
		return coursesDao.updateByPrimaryKey(record);
	}

	//我分享的课程列表
	@Override
	public Page<Courses> getSharedCourseList(Courses record) {
		Page<Courses> page = new Page<Courses>();
		record.setPageNo(record.getPageNo()==null?1:record.getPageNo());
		record.setLimitSt(record.getPageNo()*page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());	
		int count = coursesDao.getSharedCourseListCount(record);
		if(count < 1)
			return page;
		List<Courses> list = coursesDao.getSharedCourseList(record);
		if(list.size()>0){
			for (Courses course : list) {
				//用于前端页面显示
				course.setUserShareTime(DateUtils.formatDate(course.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count,record.getPageNo());
			page.setList(list);
		}
		return page;
		
	}
	//我收藏的课程列表
	@Override
	public Page<Courses> getCollectedCourseList(Courses record) {
		Integer userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		MyCollect collect = new MyCollect();
		collect.setUserid(userid);
		//动作类型枚举   0下载 1收藏   ACTIONTYPE ={0,1}
		collect.setActionType(collect.getACTIONTYPES()[1]);
		//类型     暂时用表名存储  tbl_docs  tbl_topics  tbl_books COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books","tbl_courses","tbl_Articles","tbl_sites"};
		collect.setCollecttype(collect.getCOLLECTTYPES()[3]);
		record.setMyCollect(collect);
		
		Page<Courses> page = new Page<Courses>();
		record.setPageNo(record.getPageNo()==null?1:record.getPageNo());
		record.setLimitSt(record.getPageNo()*page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());	
		int count = coursesDao.getCollectedCourseListCount(record);
		if(count < 1)
			return page;
		List<Courses> list = coursesDao.getCollectedCourseList(record);
		if(list.size()>0){
			for (Courses course : list) {
				//用于前端页面显示
				course.setUserShareTime(DateUtils.formatDate(course.getMyCollect().getColltime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count,record.getPageNo());
			page.setList(list);
		}
		
		return page;
	}
	//批量删除个人中心-我分享的课程
	@Transactional
	@Override
	public int delSharedCourses(String condition[]) {
		int ret = 0;
		ret = coursesDao.delSharedCourses(condition);
		if(ret > 0){
			operationSumService.updOperationSum(10, 1, condition.length,null);
		}
		return ret;
	}	
	
	//获取课程详情    浏览数++
	@Transactional
	@Override
	public Courses getCourseDetail(Courses record) {
		// 1.课程主体
		record = coursesDao.selectByRecord(record);
		if(null == record){
			return null;
		}
		// 浏览次数+1
		coursesDao.updateReadSum(record.getId());

		// 2.收藏此课程人的列表
		// 设置 收藏还是下载 MyCollect（ 动作类型枚举 0下载 1收藏）ACTIONTYPE ={0,1}
		// 设置表名 MyCollect COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books"};
		List<User> userList = userService.getCollectUsers(new MyCollect().getCOLLECTTYPES()[3],new MyCollect().getACTIONTYPES()[1], record.getId());

		// 3.相关课程列表
		@SuppressWarnings({ "unchecked", "static-access" })
		List<Courses> theSameCourses = solrJUtils.findCoursesFromList(record.getCoursesname(),record.getId().toString());
		// 4.评论列表
		CoursesShare share = new CoursesShare();
		share.setCoursesid(record.getId());

		Page<CoursesShare> shareList = coursesShareService.getList(share);

		record.setCollectUsers(userList);
		record.setRelatedList(theSameCourses);
		record.setCommentList(shareList);
		return record;
	}

	@Transactional
	@Override
	public MyCollect collectCourse(MyCollect record) {
		int ret = 0;
		if (record.getJudgeTodo() == 0) {
			// 开始收藏课程
			int id = seqService.getSeqByTableName("tbl_collect");
			record.setId(id);
			record.setActionType(record.getACTIONTYPES()[1]);// 动作类型枚举 0下载 1收藏
			record.setCollecttype(record.getCOLLECTTYPES()[3]);// 类型 暂时用表名存储   "tbl_docs","tbl_topics","tbl_books","tbl_courses","tbl_Articles","tbl_sites"
			// 插入记录
			ret = myCollectService.insert(record);

			// 对应用户课程收藏数 增加 
			operationSumService.updOperationSum(11, 0, 1,null);
			
			//课程的收藏数增加
			updTableColumnService.updNums(3,0,0,1,record.getObjectid());
			
		} else if (record.getJudgeTodo() == 1) {
			// 取消收藏课程
			// 删除记录
			
			if(record.getCollecttype()!=null && record.getObjectid()!=null && record.getActionType()!=null && record.getUserid()!=null){
				ret = myCollectService.delMycollects(record);
			}	
			
			//对应用户课程收藏数 减少
			operationSumService.updOperationSum(11, 1, 1,null);
			
			//课程的收藏数减少
			updTableColumnService.updNums(3,0,1,1,record.getObjectid());
		}
		if(ret < 1){
			record = null;
		}
		return record;
	}
	public static void main(String[] args) {
		System.out.println("ssas".indexOf("4")!=-1);
	}
	
}
