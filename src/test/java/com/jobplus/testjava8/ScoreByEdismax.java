package com.jobplus.testjava8;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.CommonParams;

/**
 * 
 * 测试edismax的代码实现自定义权重排序股则
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Oct 11, 2016 3:24:30 PM <br>
 *
 */
public class ScoreByEdismax {

	private final static String baseURL = "http://192.168.0.39:18080/solr/bookCore/";

	
	public static void main1(String[] args) throws Exception {

		 	
	        
	        scoreBySum();
	}
	
	public static void main(String[] args) throws Exception {



		scoreByProportion();

	}

	/**
	 * 
	 * @描述：按照各个数值的比重来进行权重计算 count 10% point 45% hot 45% --假设总分为100分 那么 分别最大分为 10
	 *                      45 45
	 * @return void
	 * @exception @createTime：2016年4月18日
	 * @author: songqinghu
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void scoreByProportion() throws SolrServerException, IOException {
		// 先查询出来三个字段中每个字段的最大值--编写好计算权重的公式---实际项目中应该还要加入是否存在的判断


		String scoreMethod = "sum(product(div(title," + 3 + "),50),product(div(author," + 2
				+ "),30),product(div(press," + 1 + "),20))^1000000000";

		SolrQuery query = new SolrQuery();
		query.set(CommonParams.Q, "title:人类群星闪耀时");
		query.set(CommonParams.FL, "id", "data_id", "title", "author", "press", "description","score");
		query.set("defType", "edismax");

		query.set("qf", scoreMethod);

		QueryResponse response = getClient().query(query);

		resultShow(response);

	}

	/**
	 * 
	 * @描述:按照 count point 和 hot 的 和的数量来进行权重计算
	 * @return void
	 * @exception @createTime：2016年4月18日
	 * @author: songqinghu
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void scoreBySum() throws SolrServerException, IOException {

		
		SolrQuery query = new SolrQuery();

		query.set(CommonParams.Q, "351");
		query.set(CommonParams.FQ, "data_id");

		//query.set(CommonParams.FL, "id", "itle", "replySum", "point", "hot", "score");

		// 开启edismax方式来进行自定义权重算法
		//query.set("defType", "edismax");

		//query.set("bf", "sum(title)^1");
		//query.set("qf","title^2 tags^0.2");
		
		//query.set("bf", "sum(title)^100");

		QueryResponse response = getClient().query(query);

		resultShow(response);

	}

	/**
	 * 
	 * @描述：查询结果显示类
	 * @param response
	 * @return void
	 * @exception @createTime：2016年4月18日
	 * @author: songqinghu
	 */
	private static void resultShow(QueryResponse response) {

		int time = response.getQTime();
		System.out.println("响应时间:" + time + "ms");

		SolrDocumentList results = response.getResults();
		long numFound = results.getNumFound();
		System.out.println("总数量:" + numFound);

		for (SolrDocument doc : results) {

			System.out.println("id:" + doc.getFieldValue("id").toString());
			System.out.println("data_id:" + doc.getFieldValue("data_id").toString());
			System.out.println("title:" + doc.getFieldValue("title").toString());
			System.out.println("author:" + doc.getFieldValue("author").toString());
			System.out.println("description:" + doc.getFieldValue("description").toString());
			System.out.println("press:" + doc.getFieldValue("press").toString());
			System.out.println("score:" + doc.getFieldValue("score").toString());
			System.out.println();
		}
	}

	/**
	 * 
	 * @描述：获取单机版本的连接信息
	 * @return
	 * @return SolrClient
	 * @exception @createTime：2016年4月18日
	 * @author: songqinghu
	 */
	public static SolrClient getClient() {

		SolrClient solrClient = new HttpSolrClient(baseURL);

		return solrClient;
	}

}
