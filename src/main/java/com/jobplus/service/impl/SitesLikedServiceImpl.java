package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SitesLikedMapper;
import com.jobplus.pojo.SitesLiked;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.ISitesLikedService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("sitesLikedService")
public class SitesLikedServiceImpl implements ISitesLikedService {

	@Resource
	private SitesLikedMapper sitesLikedDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(SitesLiked key) {
		int ret = 0;
		ret = sitesLikedDao.deleteByPrimaryKey(key);
		if (ret > 0) {
			// 站点点赞数 - 1
			updTableColumnService.updNums(5, 2, 1, 1, key.getCommid());
		}
		return ret;
	}

	@Transactional
	@Override
	public int insert(SitesLiked record,String contextPath,User user) {
		int ret = 0;
		ret = sitesLikedDao.insert(record);
		if (ret > 0) {
			// 站点点赞数 + 1
			updTableColumnService.updNums(5, 2, 0, 1, record.getCommid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[16],record.getCommid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[18],record.getRelationidPg(),record.getObjectNamePg());
			
		}
		return ret;
	}

	@Override
	public int insertSelective(SitesLiked record) {
		
		return sitesLikedDao.insertSelective(record);
	}

}
