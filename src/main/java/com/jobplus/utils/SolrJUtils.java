package com.jobplus.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.BusiAreaLib;
import com.jobplus.pojo.SkillLibrary;
import com.jobplus.pojo.Tags;

/**
 * 
 * 
 * Title: jobplus <br>
 * Description: <br>
 * Solr交互工具类 Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Jun 29, 2016 1:45:42 PM <br>
 *
 */
@Service("solrJUtils")
public class SolrJUtils {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SolrJUtils.class);

	private static String solrIp;

	private static String solrPort;

	@Value("${slor.basepath}")
	public void setBasePath(String basePath) {
		
		SolrJUtils.busiAreaClienct = new HttpSolrClient(basePath + "busiAreaCore");
		
		SolrJUtils.tagClient = new HttpSolrClient(basePath + "tagsCore");

		SolrJUtils.topicsClient = new HttpSolrClient(basePath + "topicsCore");

		SolrJUtils.userClient = new HttpSolrClient(basePath + "userCore");

		SolrJUtils.docClient = new HttpSolrClient(basePath + "docCore");

		SolrJUtils.bookClient = new HttpSolrClient(basePath + "bookCore");

		SolrJUtils.articleClient = new HttpSolrClient(basePath + "articleCore");

		SolrJUtils.coursesClient = new HttpSolrClient(basePath + "coursesCore");

		SolrJUtils.sitesClient = new HttpSolrClient(basePath + "sitesCore");

		SolrJUtils.skillClient = new HttpSolrClient(basePath + "skillCore");
		
		SolrJUtils.schoolClient = new HttpSolrClient(basePath + "schoolCore");
	}

	@Value("${slor.ip}")
	public void setSolrIp(String solrIp) {
		SolrJUtils.solrIp = solrIp;
	}

	@Value("${slor.port}")
	public void setSolrPort(String solrPort) {
		SolrJUtils.solrPort = solrPort;
	}

	private static HttpSolrClient busiAreaClienct;
	
	private static HttpSolrClient tagClient;

	private static HttpSolrClient topicsClient;

	private static HttpSolrClient userClient;

	private static HttpSolrClient docClient;

	private static HttpSolrClient bookClient;

	private static HttpSolrClient articleClient;

	private static HttpSolrClient coursesClient;

	private static HttpSolrClient sitesClient;

	private static HttpSolrClient skillClient;
	
	private static HttpSolrClient schoolClient;

	/**
	 * 添加业务领域到索引库
	 */
	public static void addBusiAreaIndexToSolr(BusiAreaLib busiAreaLib) {
		try {
			// 创建一个http连接
			// 构造文档和域
			SolrInputDocument doc = new SolrInputDocument();
			// 添加域
			doc.addField("id", busiAreaLib.getId());
			doc.addField("busiareaname", busiAreaLib.getBusiareaname());
			
			// 批量添加文档
			Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			docs.add(doc);
			
			busiAreaClienct.add(docs);
			busiAreaClienct.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据关键字获取对应的业务领域
	 * 
	 * @return
	 */
	public static String findBusiArea(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "busiareaname:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = busiAreaClienct.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"busiareaList\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}
	
	
	/**
	 * 添加技能到索引库
	 */
	public static void addSkillIndexToSolr(SkillLibrary skill) {
		try {
			// 创建一个http连接
			// 构造文档和域
			SolrInputDocument doc = new SolrInputDocument();
			// 确认schema.xml中是否有id，name，price这几个域
			// <field name="id" type="string" indexed="true" stored="true"
			// required="true" />
			// <field name="sku" type="text_en_splitting_tight" indexed="true"
			// stored="true" omitNorms="true"/>
			// <field name="name" type="text_general" indexed="true"
			// stored="true"/>

			// 添加域
			doc.addField("id", skill.getId());
			doc.addField("skillname", skill.getSkillname());

			// 批量添加文档
			Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			docs.add(doc);

			skillClient.add(docs);
			skillClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 根据关键字获取对应的学校
	 * 
	 * @return
	 */
	public static String findSchool(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "allcontent:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = schoolClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"schoolList\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}

	/**
	 * 根据关键字获取对应的技能
	 * 
	 * @return
	 */
	public static String findSkill(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "skillname:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = skillClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"skillList\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}

	/**
	 * 添加标签到索引库
	 */
	public static void addTagsIndexToSolr(Tags tag) {
		try {
			// 创建一个http连接
			// 构造文档和域
			SolrInputDocument doc = new SolrInputDocument();
			// 确认schema.xml中是否有id，name，price这几个域
			// <field name="id" type="string" indexed="true" stored="true"
			// required="true" />
			// <field name="sku" type="text_en_splitting_tight" indexed="true"
			// stored="true" omitNorms="true"/>
			// <field name="name" type="text_general" indexed="true"
			// stored="true"/>

			// 添加域
			doc.addField("id", tag.getId());
			doc.addField("tagname", tag.getTagname());
			doc.addField("tagtype", tag.getTagtype());

			// 批量添加文档
			Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			docs.add(doc);

			tagClient.add(docs);
			tagClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据关键字和类型获取对应的标签
	 * 
	 * @return
	 */
	public static String findTags(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "tagname:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = tagClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"tagsList\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}

	/**
	 * 根据关键字查找话题(标题或者内容中出现过此关键字的话题)
	 * 
	 * @return
	 */
	public static String findTops(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "title:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topicsClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"list\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}


	/**
	 * 根据关键字查找书
	 * 
	 * @return
	 */
	public static String findBook(String Condition) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "title:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = bookClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = "{\"list\":" + JSON.toJSONString(rsp.getResults())
				+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}";
		return str;
	}
	
	
	
	
	
	/**
	 * 相关话题列表
	 * 
	 * @return
	 */
	public static List findTopsFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "30%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topicsClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	

	/**
	 * 相关文档列表
	 * 
	 * @return
	 */
	public static List findDocFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "30%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = docClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 相关书籍列表
	 * 
	 * @return
	 */
	public static List findBookFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}
		if(Condition.indexOf("（")!=-1){
			
			Condition = Condition.substring(0,Condition.indexOf("（"));
		}
		
		if(Condition.indexOf("(")!=-1){
			
			Condition = Condition.substring(0,Condition.indexOf("("));
		}
		
		if(Condition.indexOf("【")!=-1){
			
			Condition = Condition.substring(0,Condition.indexOf("【"));
		}
		
		if(Condition.indexOf("[")!=-1){
			
			Condition = Condition.substring(0,Condition.indexOf("["));
		}
		
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "20%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = bookClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 相关课程列表
	 * 
	 * @return
	 */
	public static List findCoursesFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "30%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = coursesClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 相关文章列表
	 * 
	 * @return
	 */
	public static List findArticleFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "30%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = articleClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 相关站点列表
	 * 
	 * @return
	 */
	public static List findSitesFromList(String Condition, String id,String sharedType,String tags) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:"+ ClientUtils.escapeQueryChars(tags));
		}
		solrParams.set("q", "title:" + ClientUtils.escapeQueryChars(Condition));
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		solrParams.set("sort", "score desc");
		solrParams.set("defType", "edismax");
		solrParams.set("mm", "30%");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = sitesClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 根据关键字邀请用户回答
	 * 
	 * @return
	 */
	public static String findUser(String Condition, String userId) {
		SolrQuery solrParams = new SolrQuery();
		solrParams.set("q", "allcontent:\"" + ClientUtils.escapeQueryChars(Condition)+"\"");
		if (null != userId && userId.length() > 0) {
			solrParams.set("fq", "!id:" + userId);
		}
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = userClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
	}

	
	/**
	 * 
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param protoType
	 *            知识载体类型 文档:1 文章:2 课程:3 站点:4 话题:5 书籍:6
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @param sortType
	 *            排序，默认按热度排序，传1，按时间排序
	 * @return
	 */

	public static List<Object> findAll(String Condition, String sharedType, String protoType, String tags, String pages,
			String rows,String sortType) {
		List<Object> dataList = new ArrayList<Object>();
		if(null!=protoType && protoType.length() > 0){	
			//知识载体类型  文档:1 /文章:2 /课程:3  /站点:4  /  话题:5  /  书籍:6
			if("1".equals(protoType)){
				dataList = searchDoc( Condition,  sharedType,  tags,  pages, rows);
			}
			if("2".equals(protoType)){
				dataList = searchArticle( Condition,  sharedType,  tags,  pages, rows);
			}
			if("3".equals(protoType)){
				dataList = searchCourses( Condition,  sharedType,  tags,  pages, rows);
			}
			if("4".equals(protoType)){
				dataList = searchSites( Condition,  sharedType,  tags,  pages, rows);
			}
			if("5".equals(protoType)){
				dataList = searchTopics( Condition,  sharedType,  tags,  pages, rows,null);
			}
			if("6".equals(protoType)){
				dataList = searchBook( Condition,  sharedType,  tags,  pages, rows,null);
			}
		}else{
			StringBuffer solrBase = new StringBuffer(SolrJUtils.solrIp).append(":").append(SolrJUtils.solrPort).append("/solr/");
			StringBuffer solrShards = new StringBuffer();
			solrShards.append(solrBase).append("sitesCore,").append(solrBase).append("docCore,").append(solrBase)
					.append("articleCore,").append(solrBase).append("coursesCore,").append(solrBase).append("bookCore,").append(solrBase).append("topicsCore");
			String shards = solrShards.toString();
			SolrQuery solrParams = new SolrQuery();
			if (null != sharedType && sharedType.length() > 0) {
	
				solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
			}
			if (null != tags && tags.length() > 0) {
	
				solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
			}
	
			if (null != pages && pages.length() > 0) {
				if (null != rows && rows.length() > 0) {
	
					solrParams.set("start", multipliedString(pages, rows));
	
				} else {
	
					solrParams.set("start", multipliedString(pages, "10"));
				}
			}
	
			if (null != rows && rows.length() > 0) {
				solrParams.set("rows", rows);
	
			}
	
			if (null != Condition && Condition.length() > 0) {
	
				solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
			} else {
	
				solrParams.set("q", "*:*");
			}
			
			solrParams.set("shards", shards);// 设置shard
			if (null != sortType && sortType.length() > 0) {
				solrParams.set("sort", "score desc,updateTime desc");
			}else{
				solrParams.set("sort", "score desc,replySum desc");
			}
			QueryResponse rsp = new QueryResponse();
			try {
				rsp = topicsClient.query(solrParams);
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (null != rsp.getResults()) {
	
				dataList.add(rsp.getResults().getNumFound());
				dataList.add(JSON.toJSONString(rsp.getResults()));
			}else{
				dataList.add(0L);
				dataList.add(JSON.toJSONString(rsp.getResults()));
			}
	
		}
		return dataList;
	}
	

	/**
	 * 两个字符类型的数相乘 a*b=c；
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String multipliedString(String a, String b) {
		double a1 = Double.parseDouble(a);
		double b1 = Double.parseDouble(b);
		BigDecimal a2 = BigDecimal.valueOf(a1);
		BigDecimal b2 = BigDecimal.valueOf(b1);
		BigDecimal c2 = a2.multiply(b2);
		return c2.setScale(0).toString();
	}
	
	
	
	/**
	 * 搜索话题
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @param sortType
	 *            话题专区   话题分类参数         1：热门话题   2：最新话题    3:等待回答      4:精彩回答              默认为1           
	 * @return
	 */
	public static List<Object> searchTopics(String Condition, String sharedType, String tags, String pages,String rows,String sortType) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}
		
		if("2".equals(sortType)){
			
			solrParams.set("sort", "score desc,id desc,sum(readSum,replySum,collectSum) desc");
			
		}else if("3".equals(sortType)){
			
			solrParams.addFilterQuery("replySum:0");
			//等待回答的话题按照关注人数，创建时间排序
			solrParams.set("sort", "score desc,id desc,sum(readSum,collectSum) desc");
			
		}else if("4".equals(sortType)){
			
			solrParams.set("sort", "score desc,replySum desc,id desc");
			
		}else
		{
			solrParams.set("sort", "score desc,sum(readSum,replySum,collectSum) desc");
		}
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topicsClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
	
	
	
	/**
	 * 搜索文档
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @return
	 */
	public static List<Object> searchDoc(String Condition, String sharedType, String tags, String pages,String rows) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}

		solrParams.set("sort", "score desc,readSum desc,downSum desc,collectSum desc,likeSum desc,replySum desc");

		QueryResponse rsp = new QueryResponse();
		try {
			rsp = docClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
	
	/**
	 * 搜索书籍
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @param sortType
	 *         书籍专区   话题分类参数     1：热门书籍    2：最新书籍        默认为1
	 * @return
	 */
	public static List<Object> searchBook(String Condition, String sharedType, String tags, String pages,String rows,String sortType) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}
		
		if("2".equals(sortType)){
			solrParams.set("sort", "score desc, id desc,sum(replySum,collectSum) desc");
		}else{
			solrParams.set("sort", "score desc, sum(replySum,collectSum) desc,id desc");
		}
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = bookClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
	
	
	
	
	
	
	
	/**
	 * 搜索课程
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @return
	 */
	public static List<Object> searchCourses(String Condition, String sharedType, String tags, String pages,String rows) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}

		solrParams.set("sort", "score desc,sum(replySum,readSum,likeSum,collectSum) desc,id desc");

		QueryResponse rsp = new QueryResponse();
		try {
			rsp = coursesClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
	
	/**
	 * 搜索文章
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @return
	 */
	public static List<Object> searchArticle(String Condition, String sharedType, String tags, String pages,String rows) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}

		solrParams.set("sort", "score desc,sum(replySum,readSum,likeSum,collectSum,downSum) desc,id desc");

		QueryResponse rsp = new QueryResponse();
		try {
			rsp = articleClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
	
	/**
	 * 搜索   站点
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @return
	 */
	public static List<Object> searchSites(String Condition, String sharedType, String tags, String pages,String rows) {
		SolrQuery solrParams = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			solrParams.addFilterQuery("sharetype:" + ClientUtils.escapeQueryChars(sharedType));
		}
		if (null != tags && tags.length() > 0) {

			solrParams.addFilterQuery("tags:" + ClientUtils.escapeQueryChars(tags));
		}

		if (null != pages && pages.length() > 0) {
			if (null != rows && rows.length() > 0) {

				solrParams.set("start", multipliedString(pages, rows));

			} else {

				solrParams.set("start", multipliedString(pages, "10"));
			}
		}

		if (null != rows && rows.length() > 0) {
			solrParams.set("rows", rows);

		}

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + ClientUtils.escapeQueryChars(Condition));
		} else {

			solrParams.set("q", "*:*");
		}

		solrParams.set("sort", "score desc,sum(replySum,readSum,likeSum,collectSum) desc,id desc");

		QueryResponse rsp = new QueryResponse();
		try {
			rsp = sitesClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		if (null != rsp.getResults()) {

			dataList.add(rsp.getResults().getNumFound());
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}else{
			dataList.add(0L);
			dataList.add(JSON.toJSONString(rsp.getResults()));
		}

		return dataList;
	}
	
}
