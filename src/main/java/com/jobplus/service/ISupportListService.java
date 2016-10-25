package com.jobplus.service;

import com.jobplus.pojo.SupportList;

public interface ISupportListService {

	int deleteByPrimaryKey(Integer id);

	int insert(SupportList record);

	int insertSelective(SupportList record);

	SupportList selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SupportList record);

	int updateByPrimaryKey(SupportList record);

}
