package com.jobplus.job;

import com.jobplus.dao.HotReommInfoMapper;
import com.jobplus.dao.HotdataInfoMapper;
import com.jobplus.dao.LatesdataInfoMapper;
import com.jobplus.pojo.HotReommInfo;
import com.jobplus.pojo.HotdataInfo;
import com.jobplus.pojo.LatesdataInfo;
import com.jobplus.service.impl.RedisServiceImpl;
import com.jobplus.service.impl.SequenceServiceImpl;
import com.jobplus.utils.Common;
import com.jobplus.utils.ConstantManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 最新分享、热门分享、热门推荐 定时刷新任务
 * <p>
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 *
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @version 1.0 <br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @creatdate Aug 19, 2016 9:55:57 AM <br>
 */
@Service("indexDataJob")
public class IndexDataJob {

    private static Logger logger = LoggerFactory.getLogger(IndexDataJob.class);
    @Resource
    private RedisServiceImpl redisService;
    @Resource
    private HotdataInfoMapper hotdataInfoMapper;
    @Resource
    private LatesdataInfoMapper latesdataInfoMapper;
    @Resource
    private HotReommInfoMapper hotReommInfoMapper;
    @Resource
    private SequenceServiceImpl seqService;

    /**
     * 定时刷新首页缓存数据
     */
    //@Scheduled(cron = "0 0 */1 * * ?")   //每1小时执行一次
    @Transactional
    public void cacheIndexData() {
        logger.info("cacheIndexData...begin");
        //最热分享
        /*List<HotdataInfo> hotDatas = new ArrayList<>();
        String[] sharedTypes = {"100:创新", "200:创业", "300:咨询", "400:财务", "500:IT", "600:互联网", "700:工程", "800:技术", "900:市场", "1000:销售", "1100:供应", "1200:制造", "1300:创意", "1400:设计", "1500:媒体", "1600:影视", "1700:翻译", "1800:出版"};
        for (String sharedType : sharedTypes) {
            List<HotdataInfo> hotDataList = hotdataInfoMapper.getHotDatas(sharedType);
            if (hotDataList != null && hotDataList.size() > 0) {
                hotDatas.addAll(hotDataList);
            }
        }
        if (hotDatas.size() > 0) {
            hotdataInfoMapper.deleteAll();
            int[] ids = seqService.getSeqByTableName("tbl_hotdata", hotDatas.size());
            int index = 0;
            for (HotdataInfo data : hotDatas) {
                data.setId(ids[index]);
                data.setExtendinfo(Common.setIndexSharedType(data.getShreadtype()));
                data.setExtendinfo5(Common.setDataTypeName(data.getDatatype()));
                hotdataInfoMapper.insert(data);
                index++;
            }
            redisService.set(ConstantManager.REDIS_KEY_HOTSHARE, hotDatas);
        }*/
        List<HotdataInfo> hotDatas= hotdataInfoMapper.getAll();
        if (hotDatas != null && hotDatas.size() > 0) {
            for (HotdataInfo data : hotDatas) {
                data.setExtendinfo(Common.setIndexSharedType(data.getShreadtype()));
                data.setExtendinfo5(Common.setDataTypeName(data.getDatatype()));
            }
            redisService.set(ConstantManager.REDIS_KEY_HOTSHARE, hotDatas);
        }

        //最新分享
      /*  List<LatesdataInfo> latestDataList = latesdataInfoMapper.getLatestDatas();
        if (latestDataList != null && latestDataList.size() > 0) {
            latesdataInfoMapper.deleteAll();
            int[] ids = seqService.getSeqByTableName("tbl_latesdata", latestDataList.size());
            int index = 0;
            for (LatesdataInfo data : latestDataList) {
                data.setId(ids[index]);
                data.setExtendinfo5(Common.setDataTypeName(data.getDatatype()));
                latesdataInfoMapper.insert(data);
                index++;
            }
            redisService.set(ConstantManager.REDIS_KEY_LATESTSHARE, latestDataList);
        }*/
        List<LatesdataInfo> latestDataList = latesdataInfoMapper.getAll();
        if (latestDataList != null && latestDataList.size() > 0) {
            for (LatesdataInfo data : latestDataList) {
                data.setExtendinfo5(Common.setDataTypeName(data.getDatatype()));
            }
            redisService.set(ConstantManager.REDIS_KEY_LATESTSHARE, latestDataList);
        }

        //热门推荐
        List<HotReommInfo> hotRecommDataList = hotReommInfoMapper.getAll();
        if (hotRecommDataList != null && hotRecommDataList.size() > 0) {
            redisService.set(ConstantManager.REDIS_KEY_HOTRECOMM, hotRecommDataList);
        }

        logger.info("cacheIndexData...finish");
    }
}
