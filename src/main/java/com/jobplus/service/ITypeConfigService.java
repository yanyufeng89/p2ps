package com.jobplus.service;

import java.util.List;


import com.jobplus.pojo.TypeConfig;

public interface ITypeConfigService {
	
	/**
	 * 获取所有的父级分类
	 * @return
	 */
	public List<TypeConfig> getAllParentTypeConfigs(); 
	/**
	 * 获取所有的子级分类
	 * @return
	 */
	public List<TypeConfig> getAllChildrenTypeConfigs();
//	public Map<String,List<TypeConfig>> getAllChildrenTypeConfigs();
	/**
	 * 根据父级id获取对应的子级分类
	 * @param request
	 * @param parentTypeId
	 * @return
	 */
	public List<TypeConfig> getChildrenTypeConfigs(String parentTypeId);
	/**
	 * 获取所有的分类
	 * @return
	 */
	public List<TypeConfig> getAllTypeConfigs(); 
	
	public TypeConfig getTypeConfigById(Integer id);

	/**
	 * 获取title
	 * @param id
	 * @return
	 */
	public String getSiteTitleByTypeConfig(Integer id);
}
