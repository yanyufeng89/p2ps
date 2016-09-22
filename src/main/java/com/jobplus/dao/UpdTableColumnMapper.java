package com.jobplus.dao;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.UpdTableColumn;

public interface UpdTableColumnMapper {
	
	
	int updTableColumn(UpdTableColumn record);
	// 后台管理删除某一个
	int delOneById(@Param("tableName")String tableName, @Param("id")Integer id);
	//是否已经分享了链接 
	int hasSharedUrl(@Param("tableName")String tableName, @Param("userid")Integer userid, @Param("url")String url);
}
