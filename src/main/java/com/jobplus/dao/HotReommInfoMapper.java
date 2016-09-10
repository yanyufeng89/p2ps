package com.jobplus.dao;

import com.jobplus.pojo.HotReommInfo;

import java.util.List;
import java.util.Map;

public interface HotReommInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotReommInfo record);

    int insertSelective(HotReommInfo record);

    HotReommInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotReommInfo record);

    int updateByPrimaryKeyWithBLOBs(HotReommInfo record);

    int updateByPrimaryKey(HotReommInfo record);

    /**
     * 获取所有
     *
     * @return
     */
    List<HotReommInfo> getAll();

    /**
     * 删除所有
     *
     * @return
     */
    int deleteAll();

    /**
     * 根据类型和ID获取数据
     *
     * @return
     */
    List<HotReommInfo> getByDataidAndType(Map map);
}