package com.jobplus.service.impl;

import com.jobplus.dao.*;
import com.jobplus.pojo.*;
import com.jobplus.service.IIndexService;
import com.jobplus.utils.Common;
import com.jobplus.utils.ConstantManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eric on 2016/8/19.
 */
@Service("indexService")
public class IndexServiceImpl implements IIndexService {

    @Resource
    private RedisServiceImpl redisService;
    @Resource
    private HotdataInfoMapper hotdataInfoMapper;
    @Resource
    private LatesdataInfoMapper latesdataInfoMapper;
    @Resource
    private HotReommInfoMapper hotReommInfoMapper;
    @Resource
    private DocsMapper docsMapper;
    @Resource
    private BooksMapper booksMapper;
    @Resource
    private CoursesMapper coursesMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private SitesMapper sitesMapper;
    @Resource
    private TopicsMapper topicsMapper;
    @Resource
    private SequenceServiceImpl seqService;

    /**
     * 精彩分享
     *
     * @return
     */
    @Override
    public List<HotdataInfo> getHotShareData() {
        if (redisService.exists(ConstantManager.REDIS_KEY_HOTSHARE))
            return (List<HotdataInfo>) redisService.getObject(ConstantManager.REDIS_KEY_HOTSHARE);
        else {
            List<HotdataInfo> list = hotdataInfoMapper.getAll();
            if (list != null && list.size() > 0) {
                for (HotdataInfo info : list) {
                    info.setExtendinfo(Common.setIndexSharedType(info.getShreadtype()));
                    info.setExtendinfo5(Common.setDataTypeName(info.getDatatype()));
                }
                redisService.set(ConstantManager.REDIS_KEY_HOTSHARE, list);
            }
            return list;
        }
    }

    @Override
    public Map<String, List<HotdataInfo>> getHotShareDataMap() {
        Map<String, List<HotdataInfo>> map = new HashMap<>();
        List<HotdataInfo> list = getHotShareData();
        for (HotdataInfo info : list) {
            List<HotdataInfo> temp = null;
            if (map.containsKey(info.getExtendinfo()))
                temp = map.get(info.getExtendinfo());
            else
                temp = new ArrayList<>();
            temp.add(info);
            map.put(info.getExtendinfo(), temp);
        }
        return map;
    }

    /**
     * 最热推荐
     *
     * @return
     */
    @Override
    public List<HotReommInfo> getHotRecommData() {
        if (redisService.exists(ConstantManager.REDIS_KEY_HOTRECOMM))
            return (List<HotReommInfo>) redisService.getObject(ConstantManager.REDIS_KEY_HOTRECOMM);
        else {
            List<HotReommInfo> list = hotReommInfoMapper.getAll();
            if (list != null && list.size() > 0)
                redisService.set(ConstantManager.REDIS_KEY_HOTRECOMM, list);
            return list;
        }
    }

    /**
     * 最新分享
     *
     * @return
     */
    @Override
    public List<LatesdataInfo> getLatestShareData() {
        if (redisService.exists(ConstantManager.REDIS_KEY_LATESTSHARE))
            return (List<LatesdataInfo>) redisService.getObject(ConstantManager.REDIS_KEY_LATESTSHARE);
        else {
            List<LatesdataInfo> list = latesdataInfoMapper.getAll();
            if (list != null && list.size() > 0) {
                for (LatesdataInfo info : list) {
                    info.setExtendinfo5(Common.setDataTypeName(info.getDatatype()));
                }
                redisService.set(ConstantManager.REDIS_KEY_LATESTSHARE, list);
            }
            return list;
        }
    }

    /**
     * 同步首页数据
     *
     * @param id
     * @param type   1文档 2话题 3书籍 4课程 5文章 6站点
     * @param module 1最新分享 2精彩分享 3热门推荐
     */
    @Override
    public void syscData(int id, int type, int module) throws Exception {
        List list = null;
        Map map = new HashMap();
        map.put("dataid", id);
        if (type == 1)
            map.put("datatype", DataType.DOC.getValue());
        else if (type == 2)
            map.put("datatype", DataType.TOPICS.getValue());
        else if (type == 3)
            map.put("datatype", DataType.BOOK.getValue());
        else if (type == 4)
            map.put("datatype", DataType.COURSES.getValue());
        else if (type == 5)
            map.put("datatype", DataType.ARTICLE.getValue());
        else if (type == 6)
            map.put("datatype", DataType.SITES.getValue());
        if (module == 1) {
            list = latesdataInfoMapper.getByDataidAndType(map);
        } else if (module == 2) {
            list = hotdataInfoMapper.getByDataidAndType(map);
        } else if (module == 3) {
            list = hotReommInfoMapper.getByDataidAndType(map);
        }
        if (list != null && list.size() > 0)
            throw new Exception("already sysc");

        if (type == 1) {//文档
            Docs data = docsMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getDoctype());
                info.setDatatype(DataType.DOC.getValue());
                info.setTags(data.getDocclass());
                info.setLinkurl(data.getReadurl());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getDoctype());
                info.setDatatype(DataType.DOC.getValue());
                info.setTags(data.getDocclass());
                info.setLinkurl(data.getReadurl());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getDoctype());
                info.setDatatype(DataType.DOC.getValue());
                info.setTags(data.getDocclass());
                info.setLinkurl(data.getReadurl());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        } else if (type == 2) {//话题
            Topics data = topicsMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getTopicstype());
                info.setDatatype(DataType.TOPICS.getValue());
                info.setTags(data.getTopicsclass());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getCreateperson());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getReplysum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getTopicstype());
                info.setDatatype(DataType.TOPICS.getValue());
                info.setTags(data.getTopicsclass());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getCreateperson());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getReplysum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getTopicstype());
                info.setDatatype(DataType.TOPICS.getValue());
                info.setTags(data.getTopicsclass());
                info.setDataid(data.getId());
                info.setLastedittime(data.getLastedittime());
                info.setCreateuser(data.getCreateperson());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getReplysum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        } else if (type == 3) {//书籍
            Books data = booksMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getBookname());
                info.setShreadtype(data.getBooktype());
                info.setDatatype(DataType.BOOK.getValue());
                info.setTags(data.getBookclass());
                info.setImgurl(data.getBookimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(0);
                info.setExtendinfo(data.getAuthor());
                info.setExtendinfo2(data.getPress());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getBookname());
                info.setShreadtype(data.getBooktype());
                info.setDatatype(DataType.BOOK.getValue());
                info.setTags(data.getBookclass());
                info.setImgurl(data.getBookimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(0);
                info.setExtendinfo(data.getAuthor());
                info.setExtendinfo2(data.getPress());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getBookname());
                info.setShreadtype(data.getBooktype());
                info.setDatatype(DataType.BOOK.getValue());
                info.setTags(data.getBookclass());
                info.setImgurl(data.getBookimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(0);
                info.setExtendinfo(data.getAuthor());
                info.setExtendinfo2(data.getPress());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        } else if (type == 4) {//课程
            Courses data = coursesMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getCoursesname());
                info.setShreadtype(data.getCoursestype());
                info.setDatatype(DataType.COURSES.getValue());
                info.setTags(data.getCoursesclass());
                info.setLinkurl(data.getCoursesurl());
                info.setImgurl(data.getCoursesimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getCoursesname());
                info.setShreadtype(data.getCoursestype());
                info.setDatatype(DataType.COURSES.getValue());
                info.setTags(data.getCoursesclass());
                info.setLinkurl(data.getCoursesurl());
                info.setImgurl(data.getCoursesimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getCoursesname());
                info.setShreadtype(data.getCoursestype());
                info.setDatatype(DataType.COURSES.getValue());
                info.setTags(data.getCoursesclass());
                info.setLinkurl(data.getCoursesurl());
                info.setImgurl(data.getCoursesimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        } else if (type == 5) {//文章
            Article data = articleMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getArticletype());
                info.setDatatype(DataType.ARTICLE.getValue());
                info.setTags(data.getArticleclass());
                info.setLinkurl(data.getArticleurl());
                info.setImgurl(data.getArticleimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getArticletype());
                info.setDatatype(DataType.ARTICLE.getValue());
                info.setTags(data.getArticleclass());
                info.setLinkurl(data.getArticleurl());
                info.setImgurl(data.getArticleimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getArticletype());
                info.setDatatype(DataType.ARTICLE.getValue());
                info.setTags(data.getArticleclass());
                info.setLinkurl(data.getArticleurl());
                info.setImgurl(data.getArticleimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        } else if (type == 6) {//站点
            Sites data = sitesMapper.selectByPrimaryKey(id);
            if (data == null)
                throw new Exception("resource not found");
            if (module == 1) {
                LatesdataInfo info = new LatesdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getSitetype());
                info.setDatatype(DataType.SITES.getValue());
                info.setTags(data.getSiteclass());
                info.setLinkurl(data.getSiteurl());
                info.setImgurl(data.getSiteimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_latesdata"));
                latesdataInfoMapper.insert(info);
            } else if (module == 2) {
                HotdataInfo info = new HotdataInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getSitetype());
                info.setDatatype(DataType.SITES.getValue());
                info.setTags(data.getSiteclass());
                info.setLinkurl(data.getSiteurl());
                info.setImgurl(data.getSiteimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotdata"));
                hotdataInfoMapper.insert(info);
            } else if (module == 3) {
                HotReommInfo info = new HotReommInfo();
                info.setTitle(data.getTitle());
                info.setShreadtype(data.getSitetype());
                info.setDatatype(DataType.SITES.getValue());
                info.setTags(data.getSiteclass());
                info.setLinkurl(data.getSiteurl());
                info.setImgurl(data.getSiteimg());
                info.setDataid(data.getId());
                info.setLastedittime(data.getUpdatetime());
                info.setCreateuser(data.getUserid());
                info.setKpi(data.getCollectsum());
                info.setKpi2(data.getRecommendsum());
                info.setKpi3(data.getReadsum());
                info.setId(seqService.getSeqByTableName("tbl_hotrecomm"));
                hotReommInfoMapper.insert(info);
            }
        }
    }
}
