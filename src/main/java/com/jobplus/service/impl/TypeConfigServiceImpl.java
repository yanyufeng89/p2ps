package com.jobplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.jobplus.utils.ConstantManager;
import org.springframework.stereotype.Service;

import com.jobplus.dao.TypeConfigMapper;
import com.jobplus.pojo.TypeConfig;
import com.jobplus.service.ITypeConfigService;

@Service("typeConfigService")
public class TypeConfigServiceImpl implements ITypeConfigService{
	@Resource
	private TypeConfigMapper typeConfigDao;
	@Resource
	private RedisServiceImpl redisService;

	/**
	 * 获取所有的父级分类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeConfig> getAllParentTypeConfigs() {
		List<TypeConfig> list = null;
		if (redisService.exists(ConstantManager.REDIS_KEY_ALLPARENTTYPES))
			list = (List<TypeConfig>) redisService.getObject(ConstantManager.REDIS_KEY_ALLPARENTTYPES);
		else{
			list = typeConfigDao.getAllParentTypeConfigs();
			redisService.set(ConstantManager.REDIS_KEY_ALLPARENTTYPES,list);
		}
		return list;
	}

	/**
	 * 获取所有的子级分类
	 * @return
	 */
	@Override
	public List<TypeConfig> getAllChildrenTypeConfigs() {
		//
		List<TypeConfig> list =typeConfigDao.getAllChildrenTypeConfigs();
		return list;
	}
/*	public Map<String, List<TypeConfig>> getAllChildrenTypeConfigs() {
		Map<String,List<TypeConfig>> map = new HashMap<>();
		//
		List<TypeConfig> list =typeConfigDao.getAllChildrenTypeConfigs();
		List<TypeConfig> listTmp = new ArrayList<TypeConfig>();
		for (int i = 0; i < list.size(); i++) {
			if(map.containsKey(list.get(i).getParentTypeName())){
				listTmp = map.get(list.get(i).getParentTypeName());
				listTmp.add(list.get(i));
				map.put(list.get(i).getParentTypeName(),listTmp );
			}else{
				listTmp = new ArrayList<TypeConfig>();
				listTmp.add(list.get(i));
				map.put(list.get(i).getParentTypeName(), listTmp);					
			}
		}				
		return map;
	}
*/	/**
	 * 根据父级id获取对应的子级分类
	 * @param request
	 * @param parentTypeId
	 * @return
	 */
	@Override
	public List<TypeConfig> getChildrenTypeConfigs(String parentTypeId) {
		//所有子节点TypeConfigs
//      Map<String,List<TypeConfigs>> childrenTypeConfigsMap = this.getAllChildrenTypeConfigs();
      List<TypeConfig> childrenTypeConfigsList = typeConfigDao.getChildTypeConfigsByParentID(Integer.parseInt(parentTypeId));
      
      return childrenTypeConfigsList;
	}
	/**
	 * 获取所有的分类
	 * @return
	 */
	@Override
	public List<TypeConfig> getAllTypeConfigs() {
		List<TypeConfig> list = new ArrayList<>();
		//获取所有tag
		list = typeConfigDao.getAllTypeConfigs();
		return list;
	}

	@Override
	public TypeConfig getTypeConfigById(Integer id) {
		String key = ConstantManager.REDIS_KEY_TYPE_CONFIG + id;
		TypeConfig typeConfig = null;
		if (redisService.exists(key))
			typeConfig = (TypeConfig) redisService.getObject(key);
		else {
			typeConfig = typeConfigDao.selectByPrimaryKey(id);
		}
		if (typeConfig != null) {
			redisService.set(key, typeConfig);
		}
		return typeConfig;
	}
}
