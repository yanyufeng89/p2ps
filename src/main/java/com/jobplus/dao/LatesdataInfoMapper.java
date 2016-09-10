package com.jobplus.dao;

import com.jobplus.pojo.LatesdataInfo;

import java.util.List;
import java.util.Map;

public interface LatesdataInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LatesdataInfo record);

    int insertSelective(LatesdataInfo record);

    LatesdataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LatesdataInfo record);

    int updateByPrimaryKeyWithBLOBs(LatesdataInfo record);

    int updateByPrimaryKey(LatesdataInfo record);

    /**
     * 获取所有
     *
     * @return
     */
    List<LatesdataInfo> getAll();

    /**
     * 从各个表中获取最新分享
     *
     * @return
     */
    List<LatesdataInfo> getLatestDatas();

    /**
     * 删除所有
     *
     * @return
     */
    int deleteAll();

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int insertBatch(List<LatesdataInfo> list);

    /**
     * 根据类型和ID获取数据
     *
     * @return
     */
    List<LatesdataInfo> getByDataidAndType(Map map);
}