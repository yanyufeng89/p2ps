package com.jobplus.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.DocsLikedMapper;
import com.jobplus.pojo.DocsLiked;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IDocsLikedService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;

@Service("docsLikedService")
public class DocsLikedServiceImpl implements IDocsLikedService {
	
	@Resource 
	private DocsLikedMapper docsLikedDao;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;
	
	@Override
	public int deleteByPrimaryKey(DocsLiked key) {
		int ret = 0;
		ret = docsLikedDao.deleteByPrimaryKey(key);
		if (ret > 0) {
			// 站点点赞数 - 1
			updTableColumnService.updNums(0, 3, 1, 1, key.getDocid());
		}
		return ret;
	}

	@Override
	public int insert(DocsLiked record,String contextPath,User user) {
		int ret = 0;
		ret = docsLikedDao.insert(record);
		if (ret > 0) {
			// 站点点赞数 + 1
			updTableColumnService.updNums(0 , 3, 0, 1, record.getDocid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[16],record.getDocid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[18],record.getRelationidPg(),record.getObjectNamePg());
			
		}
		return ret;
	}

	@Override
	public int insertSelective(DocsLiked record) {
		
		return docsLikedDao.insertSelective(record);
	}

}
