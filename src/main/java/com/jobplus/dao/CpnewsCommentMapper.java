package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.CpnewsComment;

public interface CpnewsCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CpnewsComment record);

    int insertSelective(CpnewsComment record);

    CpnewsComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CpnewsComment record);

    int updateByPrimaryKeyWithBLOBs(CpnewsComment record);

    int updateByPrimaryKey(CpnewsComment record);
    
    /**
     * 获取快讯评论list
     */
    List<CpnewsComment> getList(CpnewsComment record);
    int getListCount(CpnewsComment record);
}