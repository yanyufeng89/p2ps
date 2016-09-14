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

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration(value = "src/main/webapp")

@ContextHierarchy({ @ContextConfiguration(locations = "classpath:spring-application.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })

// 测试版本回滚

// @ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class TestManageController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestManageController.class);

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
	public void index() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/homePage/getHome"))// .param("userId",
																							// "2"))//.param("objectId",
																							// "21").param("actionType",
																							// "1").param("userid",
																							// "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
	}
	@Test
	public void getAllsug() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/manage/getAllSug"))//.param("isdeal","0"))//.param("objectId",
				// "21").param("actionType",
				// "1").param("userid",
				// "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("getAllsug*****************:" + result.toString()));
	}
	@Test
	public void getAllreport() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/manage/getAllReport"))// .param("userId",
				// "2"))//.param("objectId",
				// "21").param("actionType",
				// "1").param("userid",
				// "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("getAllReport*****************:" + result.toString()));
	}


	
	
	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = 1;
		System.out.println("a==b  " + (a.intValue() == b.intValue()));
	}
}
