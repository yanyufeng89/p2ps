package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.CoursesLikedMapper;
import com.jobplus.pojo.CoursesLiked;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.ICoursesLikedService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("coursesLikedService")
public class CoursesLikedServiceImpl implements ICoursesLikedService {

	@Resource
	private CoursesLikedMapper coursesLikedDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;
	
	@Transactional
	@Override
	public int deleteByPrimaryKey(CoursesLiked key) {
		int ret = 0;
		ret = coursesLikedDao.deleteByPrimaryKey(key);
		if (ret > 0) {
			// 课程点赞数 - 1
			updTableColumnService.updNums(3, 2, 1, 1, key.getCoursesid());
		}
		return ret;
	}

	@Transactional
	@Override
	public int insert(CoursesLiked record,String contextPath,User user) {
		int ret = 0;
		ret = coursesLikedDao.insert(record);
		if (ret > 0) {
			// 课程点赞数 + 1
			updTableColumnService.updNums(3, 2, 0, 1, record.getCoursesid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[12],record.getCoursesid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[12],record.getRelationidPg(),record.getObjectNamePg());
			
		}
		return ret;
	}

	@Override
	public int insertSelective(CoursesLiked record) {
		
		return coursesLikedDao.insertSelective(record);
	}

}
