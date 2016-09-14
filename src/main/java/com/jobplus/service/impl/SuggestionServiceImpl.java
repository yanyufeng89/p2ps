package com.jobplus.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jobplus.dao.SuggestionMapper;
import com.jobplus.pojo.GridQuery;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Suggestion;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISuggestionService;
import com.jobplus.utils.GridDataUtil;

@Service("suggestionService")
public class SuggestionServiceImpl implements ISuggestionService {

	@Resource
	private ISequenceService seqService;
	@Resource
	private SuggestionMapper suggestionDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(Suggestion record) {
		int id = seqService.getSeqByTableName("tbl_suggestion");
		record.setId(id);
		int ret = suggestionDao.insert(record);
		return ret;
	}
	/**
	 * 获取所有建议反馈
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public Map<String, Object> getAllSug(GridQuery gridQuery) {
		Suggestion record = gridQuery.getCondition() == null ? new Suggestion() : (Suggestion) gridQuery.getCondition();
		List<Suggestion> list = null;
		int count = suggestionDao.getAllSugCount(record);
		if (count > 0) {
			record.setPageNo(gridQuery.getPage());
			record.setPageSize(gridQuery.getRows());
			record.setLimitSt(record.getPageNo() * record.getPageSize() - record.getPageSize());
			list = suggestionDao.getAllSug(record);
		}
		return GridDataUtil.getGridMap(gridQuery.getRows(), gridQuery.getPage(), count, list);
	}
	/*public Page<Suggestion> getAllSug(Suggestion record) {
		Page<Suggestion> page = new Page<Suggestion>();
		record.setPageNo(record.getPageNo() == null ? 1 : record.getPageNo());
		record.setPageSize(page.getPageSize());
		record.setLimitSt(record.getPageNo() * page.getPageSize() - page.getPageSize());
		List<Suggestion> list = suggestionDao.getAllSug(record);
		if (list.size() > 0) {
			page.initialize(list.get(0).getPageCount(), record.getPageNo());
			page.setList(list);
		}
		return page;
	}*/

}
