package com.jobplus.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobplus.dao.DocsMapper;
import com.jobplus.dao.TypeConfigMapper;
import com.jobplus.pojo.Account;
import com.jobplus.pojo.AccountDetail;
import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Docs;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.service.IAccountService;
import com.jobplus.service.IDocCommentService;
import com.jobplus.service.IDocsService;
import com.jobplus.service.IMyCollectService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ITagsService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.SolrJUtils;
import com.jobplus.utils.ThreadFTPUtils;
import com.jobplus.utils.ThreadFTPUtils.FTPType;
import com.jobplus.utils.UUIDGenerator;

/**
 * 文档分享
 * 
 * @author Jerry 2016年6月20日上午10:21:10
 *
 */
@Service("docsService")
public class DocsServiceImpl implements IDocsService {

	@Resource
	private DocsMapper docsDao;
	@Resource
	private TypeConfigMapper typeConfigDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUserService userService;
	@Resource
	private ITagsService tagsService;
	@Resource
	private IMyCollectService myCollectService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	@Resource
	private IDocCommentService docCommentService;
	@Resource
	private IAccountService accountService;
	@Resource
	private ThreadFTPUtils threadFTPUtils;
	@Resource
	private SolrJUtils solrJUtils;
	
	

	/**
	 * 批量插入文档
	 * @param list
	 * @return
	 */
	@Transactional
	@Override
	public int insertDocs(List<Docs> list) {
		int rest = 0;
		//文档分享积分值
		int changevalue = 0;
		// 获取ID数组
		int docsId[] = seqService.getSeqByTableName("tbl_docs", list.size());
		//给每个doc赋ID值
		for (int i = 0; i < list.size(); i++) {
//			time = new java.sql.Timestamp(new java.util.Date().getTime());
			Docs docs = list.get(i);
			docs.setId(docsId[i]);
			//如果是公开的 增加财富值 
			if(docs.getIspublic()==1)
				changevalue += new Account().getSCORES()[1];
			
		}

		rest = docsDao.insertDocs(list);

		if(rest>0 && changevalue>0){
			//增加财富值
			accountService.modAccountAndDetail(list.get(0).getUserid(), 0, changevalue, 
					1, 0, changevalue,0);
//			accountService.modAccountAndDetail(list.get(0).getUserid(), 0, list.size()*(new Account().getSCORES()[1]), 
//					1, 0, list.size()*(new Account().getSCORES()[1]),0);
		}
		
		return rest;
	}


	/**
	 * 文件上传
	 */
	@Transactional
	@Override
	public String upload(MultipartFile[] files, HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {

			//跳转页面  分享文档获取的财富值显示
			int num = 0;
			// 文档list
			List<Docs> docsList = new ArrayList<>();
			//标题
			String [] title = request.getParameterValues("title");
			//简介
			String [] description = request.getParameterValues("description");
			// 财富值
			String[] downValue = request.getParameterValues("downvalue");
			// 文档分类
			String [] doctype = request.getParameterValues("doctype");
			// 文档上传人
			int userid = Integer.parseInt((String) request.getSession().getAttribute("userid"));
			//是否公开
			String[] ispublic = request.getParameterValues("ispublic");
			//文档标签
			String[] docclass = request.getParameterValues("docclass");
			
			// 单个文档
			Docs doc = null;
			
			//用于文档标签使用数 +1
//			String  tagsArray ="";
			for (int i = 0; i < files.length; i++) {
				if (files[i].getOriginalFilename() != null) {
					// 重命名上传后的文件名
					String myFileName = files[i].getOriginalFilename();
					//文件类型
					String docsuffix = myFileName.substring(myFileName.lastIndexOf(".") + 1);
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名 + 时间戳
						String fileName = System.currentTimeMillis() +"." + docsuffix;//+ myFileName;
						doc = new Docs();
						doc.setFile(files[i]);
						//dir.server=http://192.168.0.39:8199/
						// 定义上传路径//路径格式  "/docsDir/2016/06/22/UUID测试文件 - 副本s.txt"   +doc.getDoctype()
						String path ="/"+threadFTPUtils.ftpFileDir+"/"+DateUtils.getDateTime2()+"/"+UUIDGenerator.getUUID()+fileName;
						//设置路径
						doc.setFilepath(path);
						//设置标题
						doc.setTitle(title==null?myFileName:title[i]);
						//设置财富值
						doc.setDownvalue(Integer.parseInt(downValue == null || StringUtils.isBlank(downValue[i])?ConstantManager.DEAAULT_DOWN_VALUE:downValue[i]));
						//设置类型
						doc.setDoctype(doctype==null||StringUtils.isBlank(doctype[i])?ConstantManager.DEFAULT_DOCTYPE_ID:doctype[i]);
						//设置描述
						doc.setDescription(StringUtils.isBlank(description[i])?"":description[i]);
						//设置上传人
						doc.setUserid(userid);
						//设置有效
						doc.setIsvalid(1);
						//设置最后更新时间
//						doc.setLastedittime(new java.sql.Date(new java.util.Date().getTime()));
						//设置是否公开   默认公开   
						try {
							doc.setIspublic(Integer.parseInt(ispublic == null ||StringUtils.isBlank(ispublic[i])?ConstantManager.DEFAULT_DOC_ISPUBLISHED_PUBLISH:ispublic[i]));
						} catch (Exception e) {
							doc.setIspublic(Integer.parseInt(ConstantManager.DEFAULT_DOC_ISPUBLISHED_PUBLISH));
						}
						
						//设置文件类型
						doc.setDocsuffix(docsuffix);
						//设置文档标签   
						doc.setDocclass(docclass==null||StringUtils.isBlank(docclass[i])?"":docclass[i]);
						//保存到本地
//						File localFile = new File(path);
//						files[i].transferTo(localFile);
						docsList.add(doc);
						
						//拼接文档标签 
//						tagsArray = tagsArray + doc.getDocclass();
						
						if(doc.getIspublic() == 1)
							num += new AccountDetail().getCHANGEVALUES()[1];
					}
				}
			}

			// 文档入库
			int rest = this.insertDocs(docsList);
			//所选标签总数 +1
//			tagsService.addOrDecreaseTagUsenumer(tagsArray);
			
			//对应用户分享文档数  增加  files.length   st*******  上传一个文件files.length==2;上传两个文件files.length==3
			operationSumService.updOperationSum(0, 0, (files.length-1)>0?files.length-1:0,null);
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
			//文档入库成功  文件上传到服务器
			if(rest > 0){
				for (int i = 0; i < files.length-1 && i<docsList.size(); i++) {
					//文件上传到服务器
					threadFTPUtils.uploadInit(docsList.get(i).getFilepath(),files[i].getInputStream(),FTPType.UPLOAD);
			        Thread thread3=new Thread(threadFTPUtils);  
			        thread3.start();
				}
			}
			
		return ""+num;
	}

	/**
	 * 我上传的文档
	 * @param record
	 * @return
	 */
	@Override
	public Page<Docs> getMyDocsUploaded(Docs record) {
		Page<Docs> page = new Page<Docs>();
		record.setPageNo(record.getPageNo()==null?1:record.getPageNo());
		record.setLimitSt(record.getPageNo()*page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());	
		int count = docsDao.getMyDocsUploadedCount(record);
		if(count < 1)
			return page;
		
		List<Docs> list = docsDao.getMyDocsUploaded(record);
		if(list.size()>0){
			
			page.initialize((long)count,record.getPageNo());
			page.setList(list);
			for (Docs docs : list) {
				//用于前端页面显示
				docs.setShowcreatetime(DateUtils.formatDate(docs.getCreatetime(), "yyyy-MM-dd"));
			}
		}
		return page;
	}

	/**
	 * 根据主键获取docs
	 * @param id
	 * @return
	 */
	@Override
	public Docs selectByPrimaryKey(Integer id) {
		Docs docs = docsDao.selectByPrimaryKey(id);
		// 设置时间用于页面显示
		docs.setShowcreatetime(DateUtils.formatDate(docs.getCreatetime(), "yyyy-MM-dd"));
		return docs;
	}
	@Override
	public Docs selectByRecord(Docs record) {
		Docs docs = docsDao.selectRecord(record);
		if(docs == null)
			return null;
		// 设置时间用于页面显示
		docs.setShowcreatetime(DateUtils.formatDate(docs.getCreatetime(), "yyyy-MM-dd"));
		return docs;
	}


	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return docsDao.deleteByPrimaryKey(id);
	}


	@Override
	public int insert(Docs record) {
		
		return docsDao.insert(record);
	}


	@Override
	public int insertSelective(Docs record) {
		
		return docsDao.insertSelective(record);
	}


	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Docs record) {
		int ret;
		ret = docsDao.updateByPrimaryKeySelective(record);
		return ret;
	}


	/**
	 * 批量逻辑删除docs
	 * @param condition
	 * @return
	 */
	@Transactional
	@Override
	public int deleteDocs(String[] condition,String userid) {
		int ret = 0;
		ret = docsDao.deleteDocs(condition);
		if(ret>0){
			//对应用户操作文档数  减少  st*******
			operationSumService.updOperationSum(0, 1, condition.length,null);
		}		
		return ret;
	}
//获取文档详情   浏览次数++
	@SuppressWarnings("unchecked")
	@Override
	public Docs getDocsDetail(Docs record) {
		//5.文档主体
		record = this.selectByRecord(record);
		if(record == null){
			return null;
		}
		
		//1. 浏览次数+1
		docsDao.updateReadSum(record.getId());

		
		
		// 2.下载此文档人的列表
		// 设置 收藏还是下载 MyCollect（ 动作类型枚举 0下载 1收藏）ACTIONTYPE ={0,1}
		// 设置表名 MyCollect COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_articles", "tbl_sites" };
		List<User> downloadUsers = userService.getCollectUsers(new MyCollect().getCOLLECTTYPES()[0],
						new MyCollect().getACTIONTYPES()[0], record.getId());
		//3.相管文档列表  
		@SuppressWarnings("static-access")
		List<Docs> theSameDocs = solrJUtils.findDocFromList(record.getTitle(), record.getId().toString());
		
		//4.评论列表
		DocComment com = new DocComment();
		com.setDocid(record.getId());
		//设置当前登录人
//		com.setCurrentUserId(userid);
		Page<DocComment> commList = docCommentService.getList(com);
			
		
		record.setCommentList(commList);
		record.setDownloadUsers(downloadUsers);
		record.setRelatedList(theSameDocs);
		return record;
	}

	/**
	 * 收藏文档
	 * @param record
	 * @return
	 */
	@Override
	public MyCollect collectDoc(MyCollect record) {
		int ret = 0;
		if (record.getJudgeTodo() == 0) {
			// 开始收藏文档
			int id = seqService.getSeqByTableName("tbl_collect");
			record.setId(id);
			// 动作类型枚举 0下载 1收藏
			record.setActionType(record.getACTIONTYPES()[1]);
			// 类型 暂时用表名存储
			// "tbl_docs","tbl_topics","tbl_books","tbl_sites","tbl_Articles","tbl_sites"
			record.setCollecttype(record.getCOLLECTTYPES()[0]);
			// 插入记录
			ret = myCollectService.insert(record);

			// 对应用户文档收藏数 增加
			operationSumService.updOperationSum(2, 0, 1,null);

			// 文档的收藏数增加
			updTableColumnService.updNums(0, 0, 0, 1, record.getObjectid());

		} else if (record.getJudgeTodo() == 1) {
			// 取消收藏文档
			// 删除记录

			if (record.getCollecttype() != null && record.getObjectid() != null && record.getUserid() != null) {
				ret = myCollectService.delMycollects(record);
			}

			// 对应用户文档收藏数 减少
			operationSumService.updOperationSum(2, 1, 1,null);

			// 文档的收藏数减少
			updTableColumnService.updNums(0, 0, 1, 1, record.getObjectid());
		}
		if(ret > 0){
			return record;
		}else{
			return null;
		}
		
	}

	/**
	 * 下载文档
	 */
	@Transactional
	@Override
	public MyCollect downloadDoc(MyCollect record,Docs doc) {
		if(doc.getDownvalue() > 0){
			//扣除财富值
			accountService.modAccountAndDetail(record.getUserid(), 0, - doc.getDownvalue(), 
					1, 1, doc.getDownvalue(),6);		
			//文档所有者增加财富值
			accountService.modAccountAndDetail(doc.getUserid(), 0,  doc.getDownvalue(), 
					1, 0, doc.getDownvalue(),8);
		}			
		
		// 下载文档插入记录
		int id = seqService.getSeqByTableName("tbl_collect");
		record.setId(id);
		record.setActionType(record.getACTIONTYPES()[0]);// 动作类型枚举 0下载 1收藏
		record.setCollecttype(record.getCOLLECTTYPES()[0]);// 类型 暂时用表名存储
															// "tbl_docs","tbl_topics","tbl_books","tbl_sites","tbl_Articles","tbl_sites"
		// 插入记录
		int ret = myCollectService.insert(record);

		// 对应用户文档下载数 增加
		operationSumService.updOperationSum(1, 0, 1,null);

		// 文档的下载数增加
		updTableColumnService.updNums(0, 1, 0, 1, record.getObjectid());

		if(ret > 0){
			return record;
		}
		else{
			return null;
		}
		
	}
	
}
