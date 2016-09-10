package com.jobplus.testmybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Topics;
import com.jobplus.pojo.response.TopicsResponse;
import com.jobplus.utils.ConstantManager;

public class TestSolr {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestSolr.class);
	private static String URI = "http://192.168.0.39:18080/solr/topicsCore/";

	@Before
	public void init() {

	}
	//http://localhost:80/solr/core1/dataimport?command=full-import
	//@Test
	public void test1() {
		try {
			
			HttpSolrClient hs = new HttpSolrClient(URI);
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", "12");
			document.addField("name", "这是我的第一个solr程序");
			document.addField("title", "测试solr第一个标题");
			document.addField("description", "测试这个是简介字段");
			document.addField("content", "其实没有什么内容");
			document.addField("allcontent", "其实没有什么内容");
			hs.add(document);
			hs.commit();
			hs.close();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		HttpSolrClient hs = new HttpSolrClient(URI);
		SolrQuery query = new SolrQuery();
		query.setQuery("allcontent:solr");
		QueryResponse rsp = null;
		try {
			rsp = hs.query(query);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docs = rsp.getResults();
		logger.info("********************"+JSON.toJSONString(rsp.getResults()));
		for (SolrDocument solrDocument : docs) {
			String id = solrDocument.getFieldValue("id").toString();
			String title = solrDocument.getFieldValue("title").toString();
			System.out.println("id为" + id);
			System.out.println("title为" + title);
			Map<String, Object> map = solrDocument.getFieldValueMap();
			System.out.println(map.toString());
			Collection<String> result = solrDocument.getFieldNames();
			for (String r : result) {
				System.out.println(r + ":" + solrDocument.getFieldValue(r));
			}
		}
		try {
			hs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***
     * 多核查询测试 
     * @throws Exception
   
     */
	@Test
	public void queryMultiCore()throws Exception{
       //查询a和b下面的数据，
        HttpSolrClient sc = new HttpSolrClient(URI);
        String shards = "192.168.0.39:18080/solr/topicsCore,192.168.0.39:18080/solr/userCore,192.168.0.39:18080/solr/docCore";
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
        solrParams.set("q", "allcontent:工作");
        solrParams.set("fq", "!id:16");
        solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "5");
        //solrParams.set("q.op", "AND");//设置查询关系
        solrParams.set("fl", "*,score");//设置过滤
        solrParams.set("shards", shards);//设置shard
        QueryResponse rsp = sc.query(solrParams);
        System.out.println("命中数量："+rsp.getResults().getNumFound());
        logger.info(JSON.toJSONString("++++++++++++++++++++++++++++++++++"+rsp.getResults()));
        for(SolrDocument sd:rsp.getResults()){
            System.out.println("++++++++++++++++++++++++++++++++++"+sd);
        }
        sc.close();
    }
	
	@Test
	public void findTagName()throws Exception{
       //查询a和b下面的数据，
        HttpSolrClient sc = new HttpSolrClient("http://192.168.0.39:18080/solr/tagsCore");
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
        solrParams.set("q", "tagname:"+"教育");
        solrParams.set("fq", "tagtype:1");  
        //solrParams.set("q.op", "AND");//设置查询关系
        solrParams.set("fl", "*,score");//设置过滤
        QueryResponse rsp = sc.query(solrParams);
        System.out.println("命中数量："+rsp.getResults().getNumFound());
        logger.info(JSON.toJSONString(rsp.getResults()));
        for(SolrDocument sd:rsp.getResults()){
            System.out.println(sd);
        }
        sc.close();
    }

	
	
	@Test
	public void findBookList()throws Exception{
       //查询a和b下面的数据，
        HttpSolrClient sc = new HttpSolrClient("http://192.168.0.39:18080/solr/bookCore");
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
        solrParams.set("q", "allcontent:日月");
        solrParams.set("fq", "!id:2");
        solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "5");
        QueryResponse rsp = sc.query(solrParams);
        System.out.println("命中数量_)_)_)_)_)_)_)_："+rsp.getResults().getNumFound());
        logger.info(JSON.toJSONString(rsp.getResults()));
        for(SolrDocument sd:rsp.getResults()){
            System.out.println(sd);
        }
        sc.close();
    }
	
	@Test
	public void findCoursesList()throws Exception{
       //查询a和b下面的数据，
        HttpSolrClient sc = new HttpSolrClient("http://192.168.0.39:18080/solr/coursesCore");
        ModifiableSolrParams solrParams = new ModifiableSolrParams();
        solrParams.set("q", "allcontent:知乎 - 与世界分享你的知识、经验和见解-测试数据");
        solrParams.set("fq", "!id:1");
        solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "5");
        QueryResponse rsp = sc.query(solrParams);
        System.out.println("命中数量_)_)()()()()(+++++++++++++++++++)_)_)_)_)_)_："+rsp.getResults().getNumFound());
        logger.info(JSON.toJSONString(rsp.getResults()));
        for(SolrDocument sd:rsp.getResults()){
            System.out.println(sd);
        }
        sc.close();
    }
}
