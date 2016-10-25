package com.jobplus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.TypeConfig;
import com.jobplus.service.IHomePageService;
import com.jobplus.service.IIndexService;
import com.jobplus.service.ITypeConfigService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.SolrJUtils;
import com.jobplus.utils.TypeConfigUtil;

@Service("homePageService")
public class HomePageServiceImpl implements IHomePageService {
	@Resource
	private ITypeConfigService typeConfigService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private IIndexService indexService;
	@Resource
    private RedisServiceImpl redisService;
	@Resource
	private TypeConfigUtil typeConfigUtil;


	/**
	 * 加载主页信息
	 * @param mv
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public ModelAndView getHome(ModelAndView mv) {		
		/*// 所有的父级分类 
		List<TypeConfig> allPaTypeList = null;
		if (redisService.exists(ConstantManager.REDIS_KEY_ALLPARENTTYPES))
			allPaTypeList = (List<TypeConfig>) redisService.getObject(ConstantManager.REDIS_KEY_ALLPARENTTYPES);
		else {
			allPaTypeList = typeConfigService.getAllParentTypeConfigs();
			if (allPaTypeList != null && allPaTypeList.size() > 0)
				redisService.set(ConstantManager.REDIS_KEY_ALLPARENTTYPES, allPaTypeList);
		}
		//所有的子级分类
		List<TypeConfig> allChlTypeList = null;
		if (redisService.exists(ConstantManager.REDIS_KEY_ALLCHAILDTYPES))
			allChlTypeList = (List<TypeConfig>) redisService.getObject(ConstantManager.REDIS_KEY_ALLCHAILDTYPES);
		else {
			allChlTypeList = typeConfigService.getAllChildrenTypeConfigs();
			if (allChlTypeList != null && allChlTypeList.size() > 0)
				redisService.set(ConstantManager.REDIS_KEY_ALLCHAILDTYPES, allChlTypeList);
		}
		mv.addObject("allPaTypeList", allPaTypeList);
		mv.addObject("allChlTypeList", allChlTypeList);
		*/

		
		//首页分类信息
		List<Map<String,String>> homeTypeList = new ArrayList<>();
		if (redisService.exists(ConstantManager.REDIS_KEY_HOMETYPELIST))
			homeTypeList = (List<Map<String,String>>) redisService.getObject(ConstantManager.REDIS_KEY_HOMETYPELIST);
		else {
			homeTypeList = typeConfigUtil.getHomeTypeList();
			if (homeTypeList != null && homeTypeList.size() > 0)
				redisService.set(ConstantManager.REDIS_KEY_HOMETYPELIST, homeTypeList);
		}		
		
		// 最新分享
		// 最热推荐
		// 精彩分享
		mv.addObject("latestDatas", indexService.getLatestShareData());
		mv.addObject("hotShareDataMap", indexService.getHotShareDataMap());
		mv.addObject("recommDatas", indexService.getHotRecommData());
		mv.addObject("homeTypeList", homeTypeList);
		
		
		return mv;
	}
	/**
	 * 主页    搜索
	 * @param Condition 查询关键字
	 * @param sharedType 行业分类 
	 * @param protoType  知识载体类型 文档:1 文章:2 课程:3 站点:4 话题:5 书籍:6
	 * @param tags 标签
	 * @param pages 第几页，默认第0页
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List search(String Condition, String sharedType, String protoType, String tags, String pages, String rows) {
		@SuppressWarnings("static-access")
		List list = solrJUtils.findAll(Condition, sharedType, protoType, tags, pages,rows);
		return list;
	}
	/**
	 * 获取搜索页的分类信息
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Map<String, Object>> getSearchTypes() {
		
		List<Map<String, Object>> searchTypeList = new ArrayList<>();
		if (redisService.exists(ConstantManager.REDIS_KEY_SEARCHTYPELIST))
			searchTypeList = (List<Map<String, Object>>) redisService.getObject(ConstantManager.REDIS_KEY_SEARCHTYPELIST);
		else {
			// 所有的父级分类   18个
			List<TypeConfig> allPaTypeList = null;
			if (redisService.exists(ConstantManager.REDIS_KEY_ALLPARENTTYPES))
				allPaTypeList = (List<TypeConfig>) redisService.getObject(ConstantManager.REDIS_KEY_ALLPARENTTYPES);
			else {
				allPaTypeList = typeConfigService.getAllParentTypeConfigs();
				if (allPaTypeList != null && allPaTypeList.size() > 0)
					redisService.set(ConstantManager.REDIS_KEY_ALLPARENTTYPES, allPaTypeList);
			}
			for(int i =0;i<allPaTypeList.size();i++){
				Map<String, Object> map = new HashMap<>();
				//父级标签名字
				map.put("parTagName", allPaTypeList.get(i).getTypename());
				//父级标签ID
				map.put("parTagId", allPaTypeList.get(i).getTypeid());
				//对应子级标签list
				map.put("chlTagList", typeConfigService.getChildrenTypeConfigs(allPaTypeList.get(i).getTypeid().toString()));
				searchTypeList.add( map);
			}
			if (searchTypeList != null && searchTypeList.size() > 0)
				redisService.set(ConstantManager.REDIS_KEY_SEARCHTYPELIST, searchTypeList);
		}
		
		return searchTypeList;
	}

	
}
