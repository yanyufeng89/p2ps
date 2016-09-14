package com.jobplus.dao;

import java.util.List;

import com.jobplus.pojo.Suggestion;

public interface SuggestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKeyWithBLOBs(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
    
    /**
     * 获取所有建议反馈
     * @param record
     * @return
     */
    public int getAllSugCount(Suggestion record);
    public List<Suggestion> getAllSug(Suggestion record);
    
    
}