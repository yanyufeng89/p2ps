package com.jobplus.dao;

import com.jobplus.pojo.HotdataInfo;

import java.util.List;
import java.util.Map;

public interface HotdataInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HotdataInfo record);

    int insertSelective(HotdataInfo record);

    HotdataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotdataInfo record);

    int updateByPrimaryKeyWithBLOBs(HotdataInfo record);

    int updateByPrimaryKey(HotdataInfo record);

    /**
     * 获取所有
     *
     * @return
     */
    List<HotdataInfo> getAll();

    /**
     * 删除所有
     *
     * @return
     */
    int deleteAll();

    /**
     * 从各个表获取最热分享数据
     *
     * @return
     */
    List<HotdataInfo> getHotDatas(String sharedType);

    /**
     * 根据类型和ID获取数据
     *
     * @return
     */
    List<HotdataInfo> getByDataidAndType(Map map);
}