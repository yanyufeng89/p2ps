package com.jobplus.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jobplus.controller.UserController;
import com.jobplus.dao.TagsMapper;
import com.jobplus.pojo.Tags;
import com.jobplus.service.ISensitiveWordsService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ITagsService;

/**
 * 标签管理
 * @author Jerry
 * 2016年6月20日下午4:34:20
 *
 */
@Service("tagsService")
public class TagsServiceImpl implements ITagsService{
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private TagsMapper tagsDao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private ISensitiveWordsService sensitiveWordsService;

	
	//暂时不用
	/*@Override
	public List<Tags> getAllParentTags() {
		List<Tags> list = new ArrayList<>();
		//获取所有父tag
		list = tagsDao.getAllParentTags();
		return list;
	}

	@Override
	public Map<String,List<Tags>> getAllChildrenTags(){
		Map<String,List<Tags>> map = new HashMap<>();
		//
		List<Tags> list =tagsDao.getAllChildrenTags();
		List<Tags> listTmp = new ArrayList<Tags>();
		for (int i = 0; i < list.size(); i++) {
			if(map.containsKey(list.get(i).getParentTagname())){
				listTmp = map.get(list.get(i).getParentTagname());
				listTmp.add(list.get(i));
				map.put(list.get(i).getParentTagname(),listTmp );
			}else{
				listTmp = new ArrayList<Tags>();
				listTmp.add(list.get(i));
				map.put(list.get(i).getParentTagname(), listTmp);
					
			}
		}
				
		return map;
	}*/

	/**
	 * 获取所有的标签
	 * @return
	 */
	@Override
	public List<Tags> getAllTags() {
		List<Tags> list = new ArrayList<>();
		//获取所有tag
		list = tagsDao.getAllTags();
		return list;
	}

	/**
	 * 根据条件模糊查询标签    tagsType标签分类
	 * @return
	 */
	@Override
	public List<Tags> getTagsByCondition(String condition,String tagType) {
		List<Tags> list = new ArrayList<>();
		//获取所有tag
		list = tagsDao.getTagsByCondition(condition,tagType);
		return list;
	}

	/**
	 * 对应tags总数加1    200:语文,300:数学,400:英语  ——>[200,300,400] 
	 * @param addArray
	 * @return
	 */
	@Transactional
	@Override
	public int addOrDecreaseTagUsenumer(String addArray) {
		logger.info("*******addOrDecreaseTagUsenumer   addArray=="+addArray);
		int ret = 0;
		String tmp = "";
		if (addArray.length() > 2) {
			String ary1[] = addArray.split(",");

			for (int i = 0; i < ary1.length; i++) {
				tmp = ary1[i].split(":")[0] +"," + tmp;
			}
			logger.info("*******addOrDecreaseTagUsenumer   tmp=="+tmp);
			
			String ary2[] = tmp.split(",");
			
			ret = tagsDao.addOrDecreaseTagUsenumer(ary2);
		}
		return ret;
		
	}
	/**
	 * 判断用户输入的标签是否合法
	 * @param tag
	 * @return
	 */
	@Override
	public int isValid(HttpServletRequest request, Tags tag) {
		int ret = 0;
		if(!sensitiveWordsService.isSensitive(tag.getTagname())){
			//合法
			ret = 1;
		}
		return ret;
	}

	@Override
	public int insert(Tags record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_tags");
		record.setId(id);
		ret = tagsDao.insert(record);
		return ret;
	}


}
