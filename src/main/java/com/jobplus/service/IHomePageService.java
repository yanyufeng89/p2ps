package com.jobplus.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public interface IHomePageService {

	/**
	 * 加载主页信息
	 * @param mv
	 * @return
	 */
	ModelAndView getHome(ModelAndView mv);
	
	/**
	 * 主页    搜索
	 * @param Condition 查询关键字
	 * @param sharedType 分类
	 * @param protoType 那种分类   doc/book/article/courses/sites/topics
	 * @param tags 标签
	 * @param pages 第几页，默认第0页
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List search(String Condition,String sharedType,String protoType,String tags,String pages, String rows);
	
	/**
	 * 获取搜索页的分类信息
	 * @return
	 */
	List<Map<String,Object>> getSearchTypes();
}
