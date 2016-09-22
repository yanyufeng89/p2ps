package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.DocComment;

public interface DocCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocComment record);

    int insertSelective(DocComment record);

    DocComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DocComment record);

    int updateByPrimaryKeyWithBLOBs(DocComment record);

  //暂时注释  此方法为全量更新
//    int updateByPrimaryKey(DocComment record);
    
    /**
	 * 获取评论列表
	 * 
	 * @param record
	 * @return
	 */
	List<DocComment> getList(DocComment record);
	int getListCount(DocComment record);
}