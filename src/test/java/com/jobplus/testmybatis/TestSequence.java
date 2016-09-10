package com.jobplus.testmybatis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jobplus.service.ISequenceService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = { "classpath:spring-application.xml" })

public class TestSequence {
	private static Logger logger = LoggerFactory.getLogger(TestSequence.class);
	// private ApplicationContext ac = null;
	@Resource
	private ISequenceService seqService;

	// @Before
	// public void before() {
	// ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	// userService = (IUserService) ac.getBean("userService");
	// }

	@Test
	public void test1() {
		
		logger.info(JSON.toJSONString(seqService.getSeqByTableName("tbl_docs")));
	}
	
	@Test
	public void test2() {
		
		logger.info(JSON.toJSONString(seqService.getSeqByTableName("tbl_docs",5)));
	}

}
