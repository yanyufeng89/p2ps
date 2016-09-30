package com.jobplus.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jobplus.dao.BookShareMapper;
import com.jobplus.dao.BooksMapper;
import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Books;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.IBooksService;
import com.jobplus.service.IMyCollectService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.DBUtilsTemplate;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.SolrJUtils;

@Service("booksService")
public class BooksServiceImpl implements IBooksService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);
	@Resource
	private BooksMapper booksDao;
	@Resource
	private BookShareMapper bookShareDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUserService userService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private IMyCollectService myCollectService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private DBUtilsTemplate dBUtilsTemplate;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private IBookShareService bookShareService;


	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return booksDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(Books record) {
		int ID = seqService.getSeqByTableName("tbl_books");
		record.setId(ID);
		return booksDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(Books record) {
		
		return booksDao.insertSelective(record);
	}

	@Override
	public Books selectByPrimaryKey(Integer id) {
		
		return booksDao.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Books record) {
		
		return booksDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeyWithBLOBs(Books record) {
		
		return booksDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public Books getBookDetail(Books record) {
		// 1.书籍主体
		record = booksDao.selectRecord(record.getId());
		if(null == record ){
			return null;
		}
		// 2.收藏此书人的列表
		// 设置 收藏还是下载 MyCollect（ 动作类型枚举 0下载 1收藏）ACTIONTYPE ={0,1}
		// 设置表名 MyCollect COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books"};
		List<User> userList = userService.getCollectUsers(new MyCollect().getCOLLECTTYPES()[2], new MyCollect().getACTIONTYPES()[1], record.getId());

		// 3.相关书籍列表
		@SuppressWarnings({ "unchecked", "static-access" })
		List<Books> theSameBooks = solrJUtils.findBookFromList(record.getBookname(), record.getId().toString());
		// 4.评论列表 page
		BookShare share = new BookShare();
		share.setBookid(record.getId());
			
		Page<BookShare> commentList = bookShareService.getList(share);
		for (BookShare bookShare : commentList.getList()) {//设置时间用于页面显示
			bookShare.setUserShareTime(DateUtils.formatDate(bookShare.getCreatetime(), "yyyy-MM-dd"));
		}
		record.setRelatedList(theSameBooks);
		record.setCollectUsers(userList);
		record.setCommentList(commentList);

		return record;
	}

	/**
	 * 书籍收藏、取消收藏  对象ID objectId(即BookId) actionType 1关注，0取消关注
	 * 
	 * @param record
	 * @return
	 */
	@Transactional
	@Override
	public MyCollect collectBook(MyCollect record) {
		int ret = 0;
		if (record.getJudgeTodo() == 0) {
			// 开始收藏书籍
			int id = seqService.getSeqByTableName("tbl_collect");
			record.setId(id);
			record.setActionType(record.getACTIONTYPES()[1]);// 动作类型枚举 0下载 1收藏
			record.setCollecttype(record.getCOLLECTTYPES()[2]);// 类型 暂时用表名存储   tbl_docs tbl_topics  tbl_books
			// 插入记录
			ret = myCollectService.insert(record);

			// 对应用户书籍收藏数 增加 
			operationSumService.updOperationSum(7, 0, 1,null);
			
			// 书籍的收藏数增加  BOOKSCOLUMNS = new String[]{"collectSum","recommendSum" };
			updTableColumnService.updNums(1,0,0,1,record.getObjectid());
			
		} else if (record.getJudgeTodo() == 1) {
			// 取消收藏书籍
			// 删除记录
			// myCollectService.deleteByPrimaryKey(record.getId());

			String sql1 = " DELETE  FROM tbl_collect   WHERE collectType = 'tbl_books' AND objectId =?  AND actionType = 1 AND userId =? ";
			ret = dBUtilsTemplate.update(sql1, new Object[] { record.getObjectid(), record.getUserid() });
			logger.info(this.getClass() + " " + sql1 + "  ");
			logger.info(this.getClass()+ JSON.toJSONString(record) + " ret=" + ret);

			// 对应用户书籍收藏数 减少
			operationSumService.updOperationSum(7, 1, 1,null);
			
			// 书籍的收藏数减少
			updTableColumnService.updNums(1,0,1,1,record.getObjectid());
		}
		if(ret < 1){
			//更新失败 返回null
			record = null;
		}
		return record;
	
	}

	/**
	 * 个人中心 -- 获取我分享的书籍列表
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Page<Books> getSharedBookList(Books record) {	
		Page<Books> page = new Page<Books>();
		record.setPageNo(record.getPageNo()==null?1:record.getPageNo());
		record.setLimitSt(record.getPageNo()*page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());
		int count = booksDao.getSharedBookListCount(record);
		if(count < 1)
			return page;
		List<Books> list = booksDao.getSharedBookList(record);
		if(list.size()>0){
			for (Books book : list) {
				book.setUserShareTime(DateUtils.formatDate(book.getBookShare().getCreatetime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count,record.getPageNo());
			page.setList(list);
		}
		
		return page;
	}
	/**
     * 个人中心  -- 获取我收藏的书籍列表
     * @param record
     * @return
     */
	@Override
	public Page<Books> getCollectedBookList(Books record) {
		
		Integer userid = Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("userid"));
		MyCollect collect = new MyCollect();
		collect.setUserid(userid);
		//动作类型枚举   0下载 1收藏   ACTIONTYPE ={0,1}
		collect.setActionType(collect.getACTIONTYPES()[1]);
		//类型     暂时用表名存储  tbl_docs  tbl_topics  tbl_books COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books"};
		collect.setCollecttype(collect.getCOLLECTTYPES()[2]);
		record.setMyCollect(collect);
		
		Page<Books> page = new Page<Books>();
		record.setPageNo(record.getPageNo()==null?1:record.getPageNo());
		record.setLimitSt(record.getPageNo()*page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());		
		int count = booksDao.getCollectedBookListCount(record);
		if(count < 1)
			return page;
		List<Books> list = booksDao.getCollectedBookList(record);
		if(list.size()>0){
			page.initialize((long)count,record.getPageNo());
			page.setList(list);
		}	
		
		return page;
	}

}
