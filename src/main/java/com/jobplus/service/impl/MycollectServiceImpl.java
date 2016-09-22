package com.jobplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.jobplus.dao.MyCollectMapper;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.service.IMyCollectService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.DateUtils;

@Service("myCollectService")
public class MycollectServiceImpl implements IMyCollectService{

	@Resource
	private MyCollectMapper mycollectDao;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUpdTableColumnService updTableColumnService;

	@Override
	public Page<MyCollect> getMyDocsList(MyCollect record) {
		Page<MyCollect> page = new Page<MyCollect>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		record.setPageSize(page.getPageSize());
		int count = mycollectDao.getMyDocsListCount(record);
		if(count < 1)
			return page;
		List<MyCollect> list = mycollectDao.getMyDocsList(record);
		if (list.size() > 0) {
			for (MyCollect myCollect : list) {// 设置时间 用于页面显示
				myCollect.setShowcolltime(DateUtils.formatDate(myCollect.getColltime(), "yyyy-MM-dd"));
			}
			page.initialize((long)count, record.getPageNo());
			page.setList(list);
		}
		return page;
	}

	//暂时没有用到  FIXME 
	@Override
	public List<MyCollect> getMyTopicsList(MyCollect record) {
		List<MyCollect> list = new ArrayList<>();
		int count = mycollectDao.getMyTopicsListCount(record);
		if(count  < 1)
			return null;
		list = mycollectDao.getMyTopicsList(record);
		return list;
	}

	/**
	 * 批量逻辑删除我的收藏和下载    
	 * @param condition
	 * @return
	 */
	@Transactional
	@Override
	public int deleteMycollects(String[] conditions, MyCollect record) {
		int ret = 0;
		//
		String objIds[] = new String[conditions.length];
		for (int i = 0; i < conditions.length; i++) {
			objIds[i] = conditions[i].split(":")[0];
		}
		ret = mycollectDao.deleteMycollects(objIds);
		if (ret > 0){
			// COLLECTTYPE={"tbl_docs","tbl_topics","tbl_books","tbl_courses","tbl_Articles","tbl_sites"};
			// 0下载 1收藏 ACTIONTYPE ={0,1}
			if (record.getCollecttype().equals(record.getCOLLECTTYPES()[0])) {// 文档
				// 对应用户操作文档数 减少 st*******
				if (ConstantManager.MY_DOCS_DOWNLOADORCOLLECT_DOWNLOAD.equals(record.getActionType().toString())) {
					// 文档下载数减少
					operationSumService.updOperationSum(1, 1, conditions.length,null);
				} else if (ConstantManager.MY_DOCS_DOWNLOADORCOLLECT_COLLECT.equals(record.getActionType().toString())) {
					// 文档收藏数减少
					operationSumService.updOperationSum(2, 1, conditions.length,null);
				}
			} else if (record.getCollecttype().equals(record.getCOLLECTTYPES()[1])) {// 话题

			} else if (record.getCollecttype().equals(record.getCOLLECTTYPES()[2])) {// 书籍
				// 书籍ID 暂时定义长度
				String bookIds[] = new String[conditions.length];
				for (int i = 0; i < conditions.length; i++) {
					bookIds[i] = conditions[i].split(":")[1];
//					conditions[i] = conditions[i].split(":")[0];
				}				
				//1. operationSum书籍收集个数减少
				operationSumService.updOperationSum(7, 1, conditions.length,null);
				//2.对应Book表collectSum 书籍收藏次数减少
				for (int i = 0; i < bookIds.length; i++) {
					int bookid = Integer.parseInt(bookIds[i]);
					updTableColumnService.updNums(1, 0, 1, 1, bookid);
				}							
			} else if (record.getCollecttype().equals(record.getCOLLECTTYPES()[3])) {// 课程
				//课程收藏数减少
				operationSumService.updOperationSum(11, 1, objIds.length,null);			

			} else if (record.getCollecttype().equals(record.getCOLLECTTYPES()[4])) {// 文章
				//文章收藏数减少
				operationSumService.updOperationSum(9, 1, objIds.length,null);	

			} else if (record.getCollecttype().equals(record.getCOLLECTTYPES()[5])) {// 站点
				//站点收藏数减少
				operationSumService.updOperationSum(13, 1, objIds.length,null);	
			}
		}
		return ret;
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return mycollectDao.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int insert(MyCollect record) {
		
		return mycollectDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(MyCollect record) {
		
		return mycollectDao.insertSelective(record);
	}

	@Override
	public MyCollect selectByPrimaryKey(Integer id) {
		
		return mycollectDao.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(MyCollect record) {
		
		return mycollectDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 外部调用 删除收藏记录   必传字段  userid collecttype objectid actionType
	 * @param record
	 * @return
	 */
	@Override
	public int delMycollects(MyCollect record) {
		
		return mycollectDao.delMycollects(record);
	}

}
