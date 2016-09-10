package com.jobplus.dao;

import com.jobplus.pojo.SitesLiked;

public interface SitesLikedMapper {
    int deleteByPrimaryKey(SitesLiked key);

    int insert(SitesLiked record);

    int insertSelective(SitesLiked record);
}