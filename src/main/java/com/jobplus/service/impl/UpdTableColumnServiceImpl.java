package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jobplus.dao.UpdTableColumnMapper;
import com.jobplus.pojo.UpdTableColumn;
import com.jobplus.service.IUpdTableColumnService;

@Service("updTableColumnService")
public class UpdTableColumnServiceImpl implements IUpdTableColumnService{
	@Resource
	private UpdTableColumnMapper updTableColumnDao;
	
	
	
	
	/**
	 * TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites", 5
				"tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
	 * DOCSCOLUMNS = new String[]{"collectSum","downSum","readSum","likeSum","recommendSum"};
	 * BOOKSCOLUMNS = new String[]{"collectSum","recommendSum" };
	 * TOPICSCOLUMNS = new String[]{"followsSum","readSum","replySum","likeSum","futilitySum","collectSum","commentSum"};	
	 * COURSESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
	 * ARTICLESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
	 * SITESCOLUMNS = new String[]{"collectSum","recommendSum","likeSum","readSum" };
		
	 * DOCSCOMMENTCOLUMNS = new String[]{ "likesum"};
	 * BOOKSHARECOLUMNS = new String[]{ "likesum"};
	 * TOPICSCOMMENTCOLUMNS = new String[]{ "replySum","likeSum","collectSum"};
	 * COURSESSHARECOLUMNS = new String[]{ "likesum"};
	 * ARTICLESHARECOLUMNS = new String[]{ "likesum"};
	 * SITESSHARECOLUMNS = new String[]{ "likesum"};
	 * @param tableName 要更改的表名
	 * @param tableColumn 要更改的表字段
	 * @param addOrDec 增加or减少
	 * @param AdOrDeNum 增减数量
	 * @param objId id
	 * @return
	 */
	@Transactional
	@SuppressWarnings("static-access")
	@Override
	public int updNums(Integer tableName,Integer tableColumn,Integer addOrDec,Integer AdOrDeNum,Integer objId) {
		int ret = 0;
		//FIXME test updTableColumnDao
		UpdTableColumn upd = new UpdTableColumn();
		upd.setTableName(upd.getTABLENAMES()[tableName]);
		upd.setTableColumn(upd.getTABLECOLUMN().get(upd.getTABLENAMES()[tableName])[tableColumn]);
		upd.setAddOrDecrease(upd.getADDORDECREASE()[addOrDec]);
		upd.setAdOrDeNum(AdOrDeNum);
		upd.setObjectId(objId);
		ret = updTableColumnDao.updTableColumn(upd);
		return ret;
	}



	/**
	 * TABLENAMES =  new String[]{"tbl_docs","tbl_books","tbl_topics","tbl_courses","tbl_article","tbl_sites", 5
				"tbl_docs_comment","tbl_books_share","tbl_topics_comment","tbl_courses_share","tbl_article_share","tbl_sites_share"};
	 * @param id
	 * @param tableName
	 * @return
	 */
	@Transactional
	@Override
	public int delOneById(Integer tableName, Integer id) {
		int ret = 0;
		try {
			ret = updTableColumnDao.delOneById(UpdTableColumn.TABLENAMES[tableName], id);
		} catch (Exception e) {
			return ret;
		}
		return ret;
	}

}
