package com.jobplus.controller;

import com.alibaba.fastjson.JSON;
import com.jobplus.job.IndexDataJob;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IHomePageService;
import com.jobplus.service.IIndexService;
import com.jobplus.service.ISensitiveWordsService;
import com.jobplus.utils.ConstantManager;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页
 *
 * @author eric 2016年8月23日
 */
@Controller
public class IndexController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Resource
	private IIndexService indexService;
	@Resource
	private IHomePageService homePageService;
	@Resource
	private IndexDataJob indexDataJob;
	@Resource
	private ISensitiveWordsService sensitiveWordsService;

	/**
	 * 刷新首页缓存
	 *
	 * @return
	 */
	@RequestMapping(value = "/index/cache", method = RequestMethod.GET)
	@ResponseBody
	public String cacheIndexData() {
		BaseResponse baseResponse = new BaseResponse();
		try {
			indexDataJob.cacheIndexData();
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**刷新首页缓存*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 首页加载
	 *
	 * @param request
	 * @param response
	 *            刷新首页缓存
	 *
	 * @return
	 */
	@RequestMapping(value = "/knowledgeBaseIndex", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv = homePageService.getHome(mv);
		mv.addObject("latestDatas", indexService.getLatestShareData());
		mv.addObject("hotShareDataMap", indexService.getHotShareDataMap());
		mv.addObject("recommDatas", indexService.getHotRecommData());
		mv.addObject("indexPage", "1");
		mv.setViewName("knowledgeBaseIndex");
		// logger.info("**首页信息** mv=="+mv);
		return mv;
	}

	@SuppressWarnings("unchecked")
	/**
	 * 搜索
	 * @param encoding
	 * @param request
	 * @param Condition 查询关键字
	 * @param sharedType 分类    如果是0,赋值为"",查询所有
	 * @param protoType 那种分类 
	 * 
	 * 	doc 1
		article 2
		courses 3
		site 4
		topics 5  doc/book/article/courses/sites/topics
	 * @param tags 标签
	 * @param pages 第几页，默认第0页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search/{sharedType}")
	@ResponseBody
	public ModelAndView search(@RequestHeader("Accept") String encoding, HttpServletRequest request,@PathVariable String sharedType,
			@RequestParam(required = false) String Condition, //@RequestParam(required = false) String sharedType,
			@RequestParam(required = false) String protoType, @RequestParam(required = false) String tags, @RequestParam(required = false)String pages)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		//如果sharedType==0  表示查询所有分类
		sharedType = "0".equals(sharedType)?"":sharedType;
		//这里设置  默认每页显示条数
		String pageSize = "10";
		
		//搜索结果 List
		List<?> resultList = homePageService.search(Condition, sharedType, protoType, tags, pages, pageSize);	
		//搜索结构总条数
		Long reCount = resultList.size()>0?(Long)resultList.get(0):0L;
		//搜索结果集
		String result =  resultList.size()>0?resultList.get(1).toString():"";
		
		//搜索页导航
		List<Map<String, Object>> searchTypeList = homePageService.getSearchTypes();
//		logger.info("********result=="+result);
		logger.info("********Condition=="+Condition);
		logger.info("********sharedType=="+sharedType);
		logger.info("********protoType=="+protoType);
		logger.info("********页数pages=="+pages);
		logger.info("********分页大小pageSize=="+pageSize);
//		logger.info("********searchTypeList=="+searchTypeList);
		logger.info("********总条数reCount=="+reCount);
		
		if (encoding.indexOf("application/json") != -1) {
			// post请求
			@SuppressWarnings("rawtypes")
			Map map = new HashMap<String, String>();
			map.put("result", result);
			map.put("reCount", reCount);
			map.put("pageSize", pageSize);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {

			mv.setViewName("searchNavigation");
			mv.addObject("result", result);
			mv.addObject("reCount", reCount);
			mv.addObject("pageSize", pageSize);
			mv.addObject("searchTypeList", searchTypeList);		
			
			mv.addObject("preSharedType", sharedType);
			mv.addObject("preCondition", Condition);
			mv.addObject("preProtoType", protoType);
			mv.addObject("prePages", pages);
//			logger.info("********parmMap=="+parmMap);
			
			return mv;
		}
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView redirectToIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("indexPage", "0");
		return mv;
	}

    /**
     * 同步首页数据
     *
     * @param id
     * @param type   1文档 2话题 3书籍 4课程 5文章 6站点
     * @param module 1最新分享 2精彩分享 3热门推荐
     * @return
     */
    @RequestMapping(value = "/index/sysc", method = RequestMethod.GET)
    @ResponseBody
    public String syscHotData(@RequestParam int id, @RequestParam int type, @RequestParam int module) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            indexService.syscData(id, type, module);
            baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
            baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
            return JSON.toJSONString(baseResponse);
        } catch (Exception e) {
            baseResponse.setReturnMsg(e.getMessage());
            baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
            logger.info("**同步首页数据*      失败   999****" + e.getMessage());
            return JSON.toJSONString(baseResponse);
        }
    }

	/**
	 * 初始化敏感词到redis
	 *
	 * @return
	 */
	@RequestMapping(value = "/index/initSensitiveWords", method = RequestMethod.GET)
	@ResponseBody
	public String syscHotData(HttpServletRequest request) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			return JSON.toJSONString(sensitiveWordsService.initRedisSensitiveWords());
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**初始化敏感词*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 错误页面
	 *
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/error/{type}", method = RequestMethod.GET)
	public ModelAndView error(@PathVariable String type) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(type);
		return mv;
	}

	/**
	 * 关于我们
	 *
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/about/{page}", method = RequestMethod.GET)
	public ModelAndView syscHotData(@PathVariable String page) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentPage", page);
		mv.setViewName("about/" + page);
		return mv;
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView knowledgeBaseIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
