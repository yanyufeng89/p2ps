package com.jobplus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jobplus.pojo.Tags;

public interface ITagsService {
	
	
//	public List<Tags> getAllParentTags(); 
	
//	public Map<String,List<Tags>> getAllChildrenTags();
	
	
	/**
	 * 获取所有的标签
	 * @return
	 */
	public List<Tags> getAllTags(); 
	
	/**
	 * 根据条件模糊查询标签    tagsType标签分类
	 * @return
	 */
	public List<Tags> getTagsByCondition(String condition,String tagsType); 
	
	
	/**
	 * 对应tags总数加1  目前只是增加
	 * @param addArray
	 * @return
	 */
	int addOrDecreaseTagUsenumer(String addArray);
	
	
	/**
	 * 判断用户输入的标签是否合法
	 * @param tag
	 * @return
	 */
	int isValid(HttpServletRequest request, Tags tag);
	
	int insert(Tags record);
}
