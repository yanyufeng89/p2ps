package com.jobplus.testmybatis;

import java.util.List;

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
import com.jobplus.pojo.User;
import com.jobplus.service.IUserService;
import com.jobplus.utils.DBUtilsTemplate;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration(value = "src/main/webapp")

@ContextHierarchy({

		@ContextConfiguration(locations = "classpath:spring-application.xml"),

		@ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })


// 测试版本回滚


// @ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class TestUserController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestUserController.class);

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
	public void testView() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/5"))
				.andExpect(MockMvcResultMatchers.view().name("showUser"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("user")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		logger.info(JSON.toJSONString(result.getModelAndView().getModel().get("user")));
	}

	@Test
	public void testAdd() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders
						.post("/user/reg?username=anan.wangfe&passwd=anan.wangfe&age=100&email=anan.wang@jobplus.com&user=null")
						.param("mobile", "13888888800").param("tel", "0572-88888888"))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/index"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("user")).andDo(MockMvcResultHandlers.print())
				.andReturn();
		logger.info(JSON.toJSONString(result.getModelAndView().getModel().get("user")));

	}

	@Test
	public void testCheck() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/user/check/anan.wang@jobplus.comss/3")
						.param("mobile", "13888888888").param("tel", "0572-88888888"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testCheck:" + result.toString()));

	}

	@Test
	public void testDBUtilsDao() throws Exception {

		DBUtilsTemplate dao = (DBUtilsTemplate) wac.getBean("dBUtilsDao");
		List<User> list = dao.find(User.class, "select * from tbl_user");
		logger.info("*********************" + JSON.toJSONString(list.get(0)));

	}
	

	@Test
	public void testByUser() throws Exception {

		
		IUserService dao = (IUserService) wac.getBean("userService");
		User u = dao.findUserByName("anan.wang@jobplus.com");
		logger.info("++++++++++++++++++++++" + JSON.toJSONString(u));

	}
	
	
	
	
	@Test
	public void testMyCenter() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getTopicsDetail").param("topicId", "5").param("sortType", "1"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}

	}
	@Test
	public void testAddFollows() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/addFollows").param("objectType", "0").param("objectId", "21").param("actionType", "1").param("userid", "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}
		
	}
	@Test
	public void justTest() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getMyHeadTop"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
//		MvcResult result2 = mockMvc
//				.perform(MockMvcRequestBuilders.post("/myCenter/addFollowsTest") .sessionAttr("userid", "30").param("objectType", "0").param("objectId", "21").param("actionType", "1").param("userid", "2"))
//				.andDo(MockMvcResultHandlers.print()).andReturn();
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}
		
	}
	@Test
	public void insertTopicsCommentTest() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/insertTopicsCommentTest").param("commcontent", "insertTopicsCommentTest"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		MvcResult result2 = mockMvc
//				.perform(MockMvcRequestBuilders.post("/myCenter/addFollowsTest") .sessionAttr("userid", "30").param("objectType", "0").param("objectId", "21").param("actionType", "1").param("userid", "2"))
//				.andDo(MockMvcResultHandlers.print()).andReturn();
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}
		
	}
	@Test
	public void getNewSms() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getNewSms"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void getAllSms() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getAllSms"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void getSmsDetail() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getSmsDetail").param("id", "1").param("islook", "0"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void makeSmsRead() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/makeSmsRead"))//.param("id", "1")
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void delSms() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/delSms"))//.param("id", "1")
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void clickLikeOnDoc() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/docs/clickLikeOnDoc").param("id", "1").param("cutUserIsliked", "0")
						.param("relationidPg", "2").param("objCreatepersonPg", "2")
						.param("ObjectNamePg", "备注 - 副本.txt"))//.param("id", "1")  RelationidPg  ObjCreatepersonPg ObjectNamePg 备注 - 副本.txt
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("insertTopicsCommentTest*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}
	@Test
	public void downloadDocs() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/docs/downloadDocs").param("id", "2")
						.param("downvalue", "5").param("userid", "2")
//						.param("ObjectNamePg", "备注 - 副本.txt")
						)//.param("id", "1")  RelationidPg  ObjCreatepersonPg ObjectNamePg 备注 - 副本.txt
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("downloadDocs*****************:" + result.toString()));
//		logger.info(JSON.toJSONString("justTest*****************:" + result.toString()));
		
	}

	
	
	
	
	@Test
	public void testGetUserSimpleInformation() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myCenter/getUserSimpleInformation").param("userId", "2"))//.param("objectId", "21").param("actionType", "1").param("userid", "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
		
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}
		
	}
	@Test
	public void updEduInfo() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/myHome/updEduInfo").param("userId", "2"))//.param("objectId", "21").param("actionType", "1").param("userid", "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
		
		//{"collectsum":0,"commentList":[],"commentsum":99,"content":"呵呵呵","createperson":30,"createtime":1467345346000,"fansList":[{"userid":2,"username":"Swallow12"},{"userid":2,"username":"Swallow12"}],"followssum":0,"futilitysum":0,"id":5,"ispublic":1,"isvalid":1,"lastedittime":1467345346000,"likesum":0,"readsum":0,"relatedTopicsList":[{"protoType":"topics","replySum":0,"tags":"201:语文,202:数学","id":"5","title":"冰与火之歌？","content":"呵呵呵","sharetype":"","_version_":1539344393091153920},{"protoType":"topics","replySum":0,"tags":"201:语文,205:数学","id":"320","title":"为什么java这么火？","content":"因为","_version_":1539344393094299652}],"replysum":0,"title":"冰与火之歌？","topicsclass":"201:语文,202:数学","topicstype":""}
		
	}
	@Test
	public void search() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/homePage/search"))//.param("userId", "2"))//.param("objectId", "21").param("actionType", "1").param("userid", "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
	}
	@Test
	public void index() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/homePage/getHome"))//.param("userId", "2"))//.param("objectId", "21").param("actionType", "1").param("userid", "2"))
				.andDo(MockMvcResultHandlers.print()).andReturn();
		logger.info(JSON.toJSONString("testMyCenter*****************:" + result.toString()));
	}
	
	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = 1;
		System.out.println("a==b  "+(a.intValue()==b.intValue()));
	}
}
