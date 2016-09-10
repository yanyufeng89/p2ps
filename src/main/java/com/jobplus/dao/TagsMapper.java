package com.jobplus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.Tags;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tags record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(Tags record);
    
    /**
     * 获取所有父节点标签    tag
     * @return
     */
    List<Tags> getAllParentTags();
    
    /**
     * 获取所有的子节点标签  tag
     * @return
     */
    List<Tags> getAllChildrenTags();
    
    /**
     * 根据父节点ID 获取子节点list
     * @return
     */
    List<Tags> getChildTagsByParentID(Integer parentid);
    /**
     * 获取所有的标签
     * @return
     */
    List<Tags> getAllTags();
    /**
     *  根据条件获取标签  @Param(value="condition") MyBatis提供的使用注解来参入多个参数的方式
     * @param condition
     * @return
     */
    List<Tags> getTagsByCondition(@Param(value="condition")String condition,@Param(value="tagType")String tagType);
    
    /**
     * tags使用数  加1
     * @param addArray
     * @return
     */
    int addOrDecreaseTagUsenumer(@Param(value="addArray")String[] addArray);
    
}