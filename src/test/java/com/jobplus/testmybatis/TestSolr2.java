package com.jobplus.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.util.ThreadContext;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Tags;
import com.jobplus.utils.SolrJUtils;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration(value = "src/main/webapp")

@ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class TestSolr2 {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestSolr2.class);
	private static String URI = "http://192.168.0.39:18080/solr/topicsCore/";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	protected MockHttpServletRequest request;

	protected MockHttpServletResponse response;

	protected MockHttpSession httpSession;

	@Resource
	org.apache.shiro.mgt.SecurityManager securityManager;
	@Resource
	private SolrJUtils solrJUtils;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		ThreadContext.bind(securityManager);

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		httpSession = new MockHttpSession();

	}

	@After
	public void detachSubject() {

	}
	// http://localhost:80/solr/core1/dataimport?command=full-import
	// @Test

	/***
	 * 多核查询测试
	 * 
	 * @throws Exception
	 * 
	 */
	public void queryMultiCore() throws Exception {
		// 查询a和b下面的数据，
		HttpSolrClient sc = new HttpSolrClient(URI);
		String shards = "192.168.0.39:18080/solr/topicsCore,192.168.0.39:18080/solr/userCore,192.168.0.39:18080/solr/docCore,192.168.0.39:18080/solr/coursesCore,192.168.0.39:18080/solr/bookCore,192.168.0.39:18080/solr/articleCore";
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", "allcontent:你是谁");

		SolrQuery filterQuery = new SolrQuery();

		filterQuery.addFilterQuery("protoType:courses");

		filterQuery.addFilterQuery("!id:39");

		solrParams.add(filterQuery);

		solrParams.set("sort", "replySum desc");
		solrParams.set("start", "0");
		solrParams.set("rows", "10");
		// solrParams.set("q.op", "AND");//设置查询关系
		solrParams.set("shards", shards);// 设置shard
		QueryResponse rsp = sc.query(solrParams);
		logger.info("命中数量：" + rsp.getResults().getNumFound());
		for (SolrDocument sd : rsp.getResults()) {
			System.out.println(JSON.toJSONString("++++++++++++++++++++++++++++++++++" + sd));
		}

		sc.close();
	}

	/***
	 * 多核查询测试
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	public void queryMultiCore2() throws Exception {
		// doc 48 /book 8/article 12/courses 44/site topics 42

		List list = solrJUtils.findAll("", "", "", "", "1", "50");
		logger.info(">>>>>>>>>>>>>>>>>" + JSON.toJSONString(list));
		/**
		 * JSONArray ob = JSON.parseArray((String)list.get(1)); ob.forEach(o ->
		 * { JSONObject s = (JSONObject)o; System.out.println(" ******  id: "
		 * +s.get("data_id")+" protoType: "+s.get("protoType")); });
		 **/
	}
	@Test
	public void addTags() throws Exception {
		// doc 48 /book 8/article 12/courses 44/site topics 42
		Tags tag = new Tags();
		tag.setId(123456789);
		tag.setTagname("测试添加标签索引");
		tag.setTagtype("0");
		solrJUtils.addTagsIndexToSolr(tag);
	}

}
