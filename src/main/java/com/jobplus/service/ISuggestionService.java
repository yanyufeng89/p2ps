package com.jobplus.service;

import java.util.Map;

import com.jobplus.pojo.GridQuery;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Suggestion;

public interface ISuggestionService {

	int deleteByPrimaryKey(Integer id);

	int insert(Suggestion record);

	int dealSug(Integer id);

	/**
	 * 获取所有建议反馈
	 * @param record
	 * @return
	 */
	public Map<String, Object> getAllSug(GridQuery gridQuery);
}
