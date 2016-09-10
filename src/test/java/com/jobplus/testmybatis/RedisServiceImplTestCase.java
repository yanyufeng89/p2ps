package com.jobplus.testmybatis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.User;
import com.jobplus.service.impl.RedisServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = { "classpath:spring-application.xml" })
public class RedisServiceImplTestCase {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RedisServiceImplTestCase.class);
	@Resource
	private RedisServiceImpl redisService;
	

	
	@Test
	public void test1() {
		List list = new ArrayList();
		User user = new User();
		user.setUserid(10012);
		user.setUsername("ananWang212");		
		User user2 = new User();
		user2.setUserid(10012);
		user2.setUsername("ananWang2");		
		User user3 = new User();
		user3.setUserid(10012);
		user3.setUsername("ananWang3");	
		list.add(user);
		list.add(user2);
		list.add(user3);
		redisService.set("ananWang", list);
		 List<User>  ll = (List)redisService.getObject("ananWang");
		 ll.forEach(o -> {
				System.out.println(o.getUsername() + "*********** ");
			});
		logger.info(JSON.toJSONString(redisService.get("ananWang")));
		
		
		redisService.del("ananWang2");
		
		redisService.setList("ananWang2", list);
		
		logger.info(JSON.toJSONString(redisService.getList("ananWang2")));
		
		logger.info(JSON.toJSONString(redisService.getList("ananWang2",0,1)));
		
		logger.info(JSON.toJSONString(redisService.getListSize("ananWang2")));
		
		
	}
	
}