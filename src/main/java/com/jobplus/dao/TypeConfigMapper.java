package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.TypeConfig;

public interface TypeConfigMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(TypeConfig record);

    int insertSelective(TypeConfig record);

    TypeConfig selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(TypeConfig record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(TypeConfig record);
    
    /**
     * 获取所有父节点    
     * @return
     */
    List<TypeConfig> getAllParentTypeConfigs();
    
    /**
     * 获取所有的子节点  
     * @return
     */
    List<TypeConfig> getAllChildrenTypeConfigs();
    
    /**
     * 根据父节点ID 获取子节点list
     * @return
     */
    List<TypeConfig> getChildTypeConfigsByParentID(Integer parentid);
    /**
     * 获取所有的分类
     * @return
     */
    List<TypeConfig> getAllTypeConfigs();
}