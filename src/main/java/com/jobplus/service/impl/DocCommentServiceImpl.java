package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.DocCommentMapper;
import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IDocCommentService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.DateUtils;

@Service("docCommentService")
public class DocCommentServiceImpl implements IDocCommentService {

	@Resource
	private DocCommentMapper docCommentDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return docCommentDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DocComment record) {
		
		return docCommentDao.insert(record);
	}

	@Override
	public int insertSelective(DocComment record) {
		
		return docCommentDao.insertSelective(record);
	}

	@Override
	public DocComment selectByPrimaryKey(Integer id) {
		
		return docCommentDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DocComment record) {
		
		return docCommentDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(DocComment record) {
		
		return docCommentDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public Page<DocComment> getList(DocComment record) {
		Page<DocComment> page = new Page<DocComment>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		int count = docCommentDao.getListCount(record);
		if(count < 1)
			return page;
		List<DocComment> list = docCommentDao.getList(record);
		if (list.size() > 0) {
			for (DocComment courseShare : list) {// 设置时间用于页面显示
				courseShare.setUserCommentTime(DateUtils.formatDate(courseShare.getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	@Transactional
	@Override
	public DocComment insertAndReturnRecord(DocComment record, String contextPath, User user) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_docs_comment");
		record.setId(id);
		ret = docCommentDao.insert(record);
		if (ret > 0 &&  record.getDocid()!=null) {
			// 对应文档的评论数 + 1
			updTableColumnService.updNums(0, 4, 0, 1, record.getDocid());
			// 用于前端显示
			record.setUserCommentTime(DateUtils.formatDate(new java.sql.Date(new java.util.Date().getTime()), "yyyy-MM-dd"));
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[6],record.getDocid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[20],record.getId(),record.getObjectNamePg());
			
			return record;
		} else {
			return null;
		}

	
	}
	/**
	 * 删除文档评论  对应评论数-1
	 */
	@Transactional
	@Override
	public int delComment(DocComment record) {
		int ret = 0;
		ret = docCommentDao.deleteByPrimaryKey(record.getId());
		if(ret > 0){
			//对应文档的评论数  - 1
			updTableColumnService.updNums(0, 4, 1, 1, record.getDocid());
		}
		return ret;
	}

}
