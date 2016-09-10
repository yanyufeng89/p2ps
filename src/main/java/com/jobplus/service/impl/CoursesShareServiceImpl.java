package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.CoursesShareMapper;
import com.jobplus.pojo.CoursesShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.ICoursesShareService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.DateUtils;

@Service("coursesShareService")
public class CoursesShareServiceImpl implements ICoursesShareService{
	
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private ISequenceService seqService;
	@Resource
	private CoursesShareMapper coursesShareDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return coursesShareDao.deleteByPrimaryKey(id);
	}

	//插入一条课程评论  同时课程评论数 + 1
	@Transactional
	@Override
	public int insert(CoursesShare record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_courses_share");
		record.setId(id);
		ret = coursesShareDao.insert(record);
		if(ret > 0){
			//对应课程的评论数 + 1
			updTableColumnService.updNums(3, 1, 0, 1, id);
		}
		return ret;
	}


	//删除一条课程评论   对应课程评论数 - 1
	@Transactional
	@Override
	public int delComment(CoursesShare record) {
		int ret = 0;
		ret = coursesShareDao.deleteByPrimaryKey(record.getId());
		if(ret > 0){
			//对应课程的评论数  - 1
			updTableColumnService.updNums(3, 1, 1, 1, record.getCoursesid());
		}
		return ret;
	}
	@Transactional
	@Override
	public int insertSelective(CoursesShare record) {
		
		return coursesShareDao.insertSelective(record);
	}

	@Override
	public CoursesShare selectByPrimaryKey(Integer id) {
		
		return coursesShareDao.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(CoursesShare record) {
		
		return coursesShareDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(CoursesShare record) {
		
		return coursesShareDao.updateByPrimaryKey(record);
	}

	@Override
	public Page<CoursesShare> getList(CoursesShare record) {
		Page<CoursesShare> page = new Page<CoursesShare>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<CoursesShare> list = coursesShareDao.getList(record);
		if (list.size() > 0) {
			for (CoursesShare courseShare : list) {// 设置时间用于页面显示
				courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	@Transactional
	@Override
	public CoursesShare insertAndReturnRecord(CoursesShare record,String contextPath,User user)  {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_courses_share");
		record.setId(id);
		ret = coursesShareDao.insert(record);
		if (ret > 0 &&  record.getCoursesid()!=null) {
			// 对应课程的评论数 + 1
			updTableColumnService.updNums(3, 1, 0, 1, record.getCoursesid());
			// 用于前端显示
			record.setUserCommentTime(DateUtils.formatDate(new java.sql.Date(new java.util.Date().getTime()), "yyyy-MM-dd"));
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[11],record.getCoursesid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[11],record.getId(),record.getObjectNamePg());
			
			return record;
		} else {
			return null;
		}

	}


}
