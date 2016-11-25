package com.jobplus.service;

import com.jobplus.pojo.HotReommInfo;
import com.jobplus.pojo.HotdataInfo;
import com.jobplus.pojo.LatesdataInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by eric on 2016/8/19.
 */
public interface IIndexService {

    /**
     * 精彩分享
     *
     * @return
     */
    public List<HotdataInfo> getHotShareData();

    /**
     * 精彩分享
     *
     * @return
     */
    public Map<String, List<HotdataInfo>> getHotShareDataMap();

    /**
     * 最热推荐
     *
     * @return
     */
    public List<HotReommInfo> getHotRecommData();

    /**
     * 最热推荐
     *
     * @return
     */
    public String getHotRecommDataBySolr();

    /**
     * 最新分享
     *
     * @return
     */
    public List<LatesdataInfo> getLatestShareData();

    /**
     * 同步首页数据
     *
     * @param id
     * @param type   1文档 2话题 3书籍 4课程 5文章 6站点
     * @param module 1最新分享 2精彩分享 3热门推荐
     */
    public void syscData(int id, int type, int module) throws Exception;
}
