package com.jobplus.testmybatis;

import javax.annotation.Resource;

import org.apache.shiro.util.ThreadContext;
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
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration(value = "src/main/webapp")

@ContextHierarchy({

		@ContextConfiguration(locations = "classpath:spring-application.xml"),

		@ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })
public class TestSmsSend {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestSmsSend.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	protected MockHttpServletRequest request;

	protected MockHttpServletResponse response;

	protected MockHttpSession httpSession;

	@Resource
	org.apache.shiro.mgt.SecurityManager securityManager;

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
	
	@Test
	public void testSendSms() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/mobilesms/sendSms")
						.param("mobileNo", "18258115959")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		logger.info(result.toString());
	}
	
	
	public void testCheckSms() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/mobilesms/checkSms")
						.param("smsId", "13").param("validateCode", "922579")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		logger.info(result.toString());
	}

	public static void main(String[] args) {
		// 官网的URL
		String url = "http://gw.api.taobao.com/router/rest";
		// 成为开发者，创建应用后系统自动生成
		String appkey = "23428976";
		String secret = "2a459829427356cbe8cc56c8a46caa3e";
		// 短信模板的内容
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("聘加信息");
		req.setSmsParamString("{\"validateCode\":\"156888\"}");
		req.setRecNum("18258115959");
		req.setSmsTemplateCode("SMS_12996230");

		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			JSONObject ob = (JSONObject) JSON.parse(rsp.getBody());
			String str = ob.get("alibaba_aliqin_fc_sms_num_send_response").toString();
			System.out.println(JSON.toJSONString(rsp.getBody()));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
