package com.jobplus.dao;

import com.jobplus.pojo.DocsLiked;

public interface DocsLikedMapper {
    int deleteByPrimaryKey(DocsLiked key);

    int insert(DocsLiked record);

    int insertSelective(DocsLiked record);
}