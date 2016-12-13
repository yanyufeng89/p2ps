package com.jobplus.dao;

import org.apache.ibatis.annotations.Param;

import com.jobplus.pojo.CompanyIntro;

public interface CompanyIntroMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyIntro record);

    int insertSelective(CompanyIntro record);

    CompanyIntro selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(@Param(value="record")CompanyIntro record,@Param(value="isEstTime")Integer isEstTime);

    int updateByPrimaryKeyWithBLOBs(CompanyIntro record);

    int updateByPrimaryKey(CompanyIntro record);
    
    /**
     * 统计企业用户完整度
     */
    int cpInfoCompletion(Integer id);
    
    /**
     * 更改企业图片
     */
    int updImgurl(CompanyIntro record);
}