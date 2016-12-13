package com.jobplus.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.CompanyNewsMapper;
import com.jobplus.dao.CpnewsCommentMapper;
import com.jobplus.pojo.CompanyNews;
import com.jobplus.pojo.Page;
import com.jobplus.service.ICompanyNewsService;
import com.jobplus.service.ISequenceService;

@Service("companyNewsService")
public class CompanyNewsServiceImpl implements ICompanyNewsService {

	@Resource
	private CompanyNewsMapper companyNewsDao;
	@Resource
	private CpnewsCommentMapper cpnewsCommentDao;
	@Resource
	private ISequenceService seqService;
	
	@Override
	public int getListCount(CompanyNews record){
		int count = companyNewsDao.getListCount(record);
		return count;
	}
	@Override
	public Page<CompanyNews> getNewsList(CompanyNews record) {
		Page<CompanyNews> page = new Page<CompanyNews>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		// 此处修改为每页8条 page.pageSize 相应的改变 原:page.getPageSize()
		record.setPageSize(4);
		page.setPageSize(4);

		record.setLimitSt(record.getPageNo() * 4 - 4);
		int count = companyNewsDao.getListCount(record);
		if(count < 1)
			return page;
		List<CompanyNews> list = companyNewsDao.getList(record);
		if (list.size() > 0) {
			/*for (CompanyNews courseShare : list) {// 设置时间用于页面显示
				courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.getCreatetime(), "yyyy-MM-dd"));
			}*/
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}


	@Override
	public CompanyNews insertCpNews(CompanyNews record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_cpnews_comment");
		record.setId(id);
		record.setCreatetime(new Date());
		record.setUpdatetime(new Date());
		record.setToptime(new Date());
		record.setIsvalid(1);
		record.setLikesum(0);
		record.setCommentsum(0);
		record.setIstop(0);
		ret = companyNewsDao.insert(record);
		if(ret>0)
			return record;
		return null;
	}


	@Override
	public int delCpNews(CompanyNews record) {
		record.setIsvalid(0);
		return companyNewsDao.updateByPrimaryKeySelective(record);
	}


	@Override
	public int topCpNews(CompanyNews record) {
		record.setIstop(1);
		record.setToptime(new Date());
		return companyNewsDao.updateByPrimaryKeySelective(record);
	}


	@Override
	public CompanyNews getOneNews(Integer id) {
		return companyNewsDao.selectByPrimaryKey(id);
	}
	
	
	
}
