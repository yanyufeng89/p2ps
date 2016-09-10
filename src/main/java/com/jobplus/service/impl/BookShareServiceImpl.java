package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.jobplus.dao.BookShareMapper;
import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.User;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.IBooksService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.DateUtils;

@Service("bookShareService")
public class BookShareServiceImpl implements IBookShareService {

	@Resource
	private BookShareMapper bookShareDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IBooksService booksService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private ISmsService smsService;

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int ret = 0;
		ret = bookShareDao.deleteByPrimaryKey(id);
		return ret;
	}

	/**
	 * 在书籍详情页下方 删除评论或者推荐语
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int deleteByRecord(BookShare record) {
		int ret = 0;
		int userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		record.setUserid(userid);
		if (record.getCommentby() != null && record.getUserid() != null && record.getBookid() != null) {
			// 仅仅是删除一条评论
			updTableColumnService.updNums(1, 1, 1, 1, record.getBookid());
		} else if (record.getUserid() != null && record.getBookid() != null) {
			// 删除书籍推荐语：1.个人书籍分享数-1; 2.书籍评论数-1
			updTableColumnService.updNums(1, 1, 1, 1, record.getBookid());
			operationSumService.updOperationSum(6, 1, 1,null);
		}

		ret = bookShareDao.deleteByPrimaryKey(record.getId());
		if (ret > 0) {

		}
		return ret;
	}

	/**
	 * 分享书籍 新增书籍推荐语：1.个人书籍分享数+1; 2.书籍评论数+1
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public int insert(BookShare record) {
		int ID = seqService.getSeqByTableName("tbl_books_share");
		record.setId(ID);
		if (record.getUserid() != null && record.getBookid() != null) {
			// 新增书籍推荐语：1.个人书籍分享数+1; 2.书籍评论数+1
			updTableColumnService.updNums(1, 1, 0, 1, record.getBookid());
			operationSumService.updOperationSum(6, 0, 1,null);
		}
		return bookShareDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(BookShare record) {
		
		return bookShareDao.insertSelective(record);
	}

	@Override
	public BookShare selectByPrimaryKey(Integer id) {
		
		return bookShareDao.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(BookShare record) {
		
		return bookShareDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 获取书籍分享详情用以编辑
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public BookShare getBookShareDetailForEdit(Integer id) {
		
		return bookShareDao.getBookShareDetailForEdit(id);
	}

	/**
	 * 新增一条书籍推荐 or 评论 ，返回一个record.id
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public BookShare insertAndReturnId(BookShare record,String contextPath,User user) {
		int ID = seqService.getSeqByTableName("tbl_books_share");
		record.setId(ID);
		if (record.getCommentby() != null && record.getUserid() != null && record.getBookid() != null) {
			// 仅仅是新增一条评论
			updTableColumnService.updNums(1, 1, 0, 1, record.getBookid());
			
			//添加消息通知
			smsService.addNotice(user,contextPath, new Sms().getTABLENAMES()[7],record.getBookid(),
					record.getObjCreatepersonPg(),new Sms().getSMSTYPES()[8],record.getId(),record.getObjectNamePg());
			
		} else if (record.getUserid() != null && record.getBookid() != null) {
			// 新增书籍推荐语：1.个人书籍分享数+1; 2.书籍评论数+1
			updTableColumnService.updNums(1, 1, 0, 1, record.getBookid());
			operationSumService.updOperationSum(6, 0, 1,null);		
		}

		if (bookShareDao.insert(record) > 0) {
			// 用于前端显示
			record.setUserShareTime(
					DateUtils.formatDate(new java.sql.Date(new java.util.Date().getTime()), "yyyy-MM-dd"));
			return record;
		} else {
			return null;
		}

	}

	/**
	 * 批量删除书籍分享
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public int deleteByConditions(String conditions[], String bookIds[]) {
		int ret = 0;
		ret = bookShareDao.deleteByConditions(conditions);
		if (ret > 0) {
			// 1. 对应用户书籍分享数 减少
			operationSumService.updOperationSum(6, 1, conditions.length,null);

			// 2.对应书籍表的recommendSum减少
			for (int i = 0; i < bookIds.length; i++) {
				int bookid = Integer.parseInt(bookIds[i]);
				updTableColumnService.updNums(1, 1, 1, 1, bookid);
			}

		}
		return ret;
	}

	/**
     * 分页显示书籍评论列表
     * @param record
     * @return
     */
	@Override
	public Page<BookShare> getList(BookShare record) {
		Page<BookShare> page = new Page<BookShare>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<BookShare> list = bookShareDao.getList(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
			for (BookShare bookShare : list) {// 设置时间用于页面显示
				bookShare.setUserShareTime(DateUtils.formatDate(bookShare.getCreatetime(), "yyyy-MM-dd"));
			}
		}

		return page;
	}

}
