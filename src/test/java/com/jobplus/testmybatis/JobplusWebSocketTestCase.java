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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.TextMessage;

//import com.jobplus.websocket.JobPlusWebSocketHandler;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration(value = "src/main/webapp")

@ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class JobplusWebSocketTestCase {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(JobplusWebSocketTestCase.class);
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
//	JobPlusWebSocketHandler handler;

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
//	@Test
//	public void TestSendMessageToUser() throws Exception {
//		handler.sendMessageToUser("29", new TextMessage("1"));
//	}

}
