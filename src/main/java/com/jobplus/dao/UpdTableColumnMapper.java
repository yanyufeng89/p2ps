package com.jobplus.dao;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.UpdTableColumn;

public interface UpdTableColumnMapper {
	
	
	int updTableColumn(UpdTableColumn record);
	
	int delOneById(@Param("tableName")String tableName, @Param("id")Integer id);
}
