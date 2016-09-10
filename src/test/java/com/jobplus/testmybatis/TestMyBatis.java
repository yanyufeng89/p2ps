package com.jobplus.testmybatis;



import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jobplus.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = { "classpath:spring-application.xml" })


public class TestMyBatis {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService ;
	
	@Value("${ftp.ip}")
	private String ftpIp;

	@Value("${ftp.file.dir}")
	private String ftpFiledir;

	@Test
	public void test1() {
		
		
		List list = userService.getFansListInformation("tbl_topics",  5);
		
		
		logger.info(JSON.toJSONString(list)+"______________________"+ftpIp+"_____    docsDir"+ftpFiledir);
	}
	
	public static void main(String args[]){
		System.out.println(StringEscapeUtils.escapeHtml4("中国好人民")); 
		System.out.println(StringEscapeUtils.escapeEcmaScript("中国好人民")); 
		System.out.println(StringEscapeUtils.escapeJava("中国好人民"));
	}
}
