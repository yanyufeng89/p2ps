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
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
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

	private static HttpSolrClient tagClient = new HttpSolrClient("http://192.168.0.39:18080/solr/tagsCore");

	private static HttpSolrClient topClient = new HttpSolrClient("http://192.168.0.39:18080/solr/topicsCore");

	private static HttpSolrClient userClient = new HttpSolrClient("http://192.168.0.39:18080/solr/userCore");

	private static HttpSolrClient docClient = new HttpSolrClient("http://192.168.0.39:18080/solr/docCore");

	private static HttpSolrClient bookClient = new HttpSolrClient("http://192.168.0.39:18080/solr/bookCore");

	private static HttpSolrClient articleClient = new HttpSolrClient("http://192.168.0.39:18080/solr/articleCore");

	private static HttpSolrClient coursesClient = new HttpSolrClient("http://192.168.0.39:18080/solr/coursesCore");

	private static HttpSolrClient sitesClient = new HttpSolrClient("http://192.168.0.39:18080/solr/sitesCore");

	/**
	 * 根据关键字和类型获取对应的标签
	 * 
	 * @return
	 */
	public static String findTags(String Condition, String type) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "tagname:" + Condition);
		solrParams.set("fq", "tagtype:" + type);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = tagClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
	}

	
	/** 
     * 添加标签到索引库 
     */  
    public  static void addTagsIndexToSolr(Tags tag){  
         try {  
            //创建一个http连接  
             //构造文档和域  
             SolrInputDocument doc = new SolrInputDocument();   
             //确认schema.xml中是否有id，name，price这几个域  
             //<field name="id" type="string" indexed="true" stored="true" required="true" />   
             //<field name="sku" type="text_en_splitting_tight" indexed="true" stored="true" omitNorms="true"/>  
             //<field name="name" type="text_general" indexed="true" stored="true"/>  
               
             //添加域  
             doc.addField( "id", tag.getId());     
             doc.addField("tagname", tag.getTagname());  
             doc.addField( "tagtype",tag.getTagtype());    
               
               
             //批量添加文档  
             Collection<SolrInputDocument> docs = new  ArrayList<SolrInputDocument>();     
             docs.add( doc );     
  
             tagClient.add(docs);  
             tagClient.commit();  
          }catch (SolrServerException e) {  
            e.printStackTrace();  
          }catch (IOException e) {  
            e.printStackTrace();  
          }  
    }  
	
	
	/**
	 * 根据关键字和类型获取对应的标签
	 * 
	 * @return
	 */
	public static String findTags(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "tagname:" + Condition);
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
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topClient.query(solrParams);
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
	public static List findTopsFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsp.getResults();
	}

	/**
	 * 根据关键字查找文档(标题或者简介中出现过此关键字的文档)
	 * 
	 * @return
	 */
	public static String findDoc(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = docClient.query(solrParams);
			// logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
	}

	/**
	 * 根据关键字查找书
	 * 
	 * @return
	 */
	public static String findBook(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
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
	 * 相关文档列表
	 * 
	 * @return
	 */
	public static List findDocFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
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
	public static List findBookFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
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
	public static List findCoursesFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
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
	public static List findArticleFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
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
	public static List findSitesFromList(String Condition, String id) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		solrParams.set("fq", "!data_id:" + id);
		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
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
	 * 根据关键字查找用户
	 * 
	 * @return
	 */
	public static String findUser(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
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
	 *            分类
	 * @param protoType
	 *            那种分类 doc/book/article/courses/site/topics
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认30条
	 * @return
	 */

	public static List findAll(String Condition, String sharedType, String protoType, String tags, String pages,
			String rows) {
		String shards = "192.168.0.39:18080/solr/topicsCore,192.168.0.39:18080/solr/docCore,192.168.0.39:18080/solr/coursesCore,192.168.0.39:18080/solr/bookCore,192.168.0.39:18080/solr/articleCore,192.168.0.39:18080/solr/sitesCore";
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		SolrQuery filterQuery = new SolrQuery();
		if (null != sharedType && sharedType.length() > 0) {

			filterQuery.addFilterQuery("sharetype:" + sharedType);
		}
		if (null != protoType && protoType.length() > 0) {

			filterQuery.addFilterQuery("protoType:" + protoType);
		}
		if (null != tags && tags.length() > 0) {

			filterQuery.addFilterQuery("tags:" + tags);
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

		solrParams.add(filterQuery);

		if (null != Condition && Condition.length() > 0) {

			solrParams.set("q", "allcontent:" + Condition);
		} else {

			solrParams.set("q", "*:*");
		}

		solrParams.set("shards", shards);// 设置shard
		solrParams.set("sort", "replySum desc");

		QueryResponse rsp = new QueryResponse();
		try {
			rsp = topClient.query(solrParams);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> dataList = new ArrayList<Object>();
		dataList.add(rsp.getResults().getNumFound());
		dataList.add(JSON.toJSONString(rsp.getResults()));

		return dataList;
	}

	/**
	 * 根据关键字查找文档
	 * 
	 * @return
	 */
	public static String findArticle(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = articleClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
	}

	/**
	 * 根据关键字查找课程
	 * 
	 * @return
	 */
	public static String findCourses(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:" + Condition);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = coursesClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
	}

	/**
	 * 根据关键字查找站点
	 * 
	 * @return
	 */
	public static String findSites(String Condition) {
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "tagname:" + Condition);
		QueryResponse rsp = new QueryResponse();
		try {
			rsp = sitesClient.query(solrParams);
			logger.info(JSON.toJSONString(rsp.getResults()));
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rsp.getResults());
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

}
