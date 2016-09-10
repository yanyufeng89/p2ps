package com.jobplus.testmybatis;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jobplus.service.ISequenceService;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = { "classpath:spring-application.xml" })

public class TestSequence2 {
	private static Logger logger = LoggerFactory.getLogger(TestSequence2.class);
	// private ApplicationContext ac = null;
	@Resource
	private ISequenceService seqService;
	
	
	HashMap map = new HashMap();
	

	
	@Test
	public void groboTest() throws Throwable {
		// 构造一个Runner
		TestRunnable runner = new TestRunnable() {
			@Override
			public void runTest() throws Throwable {
				// 测试内容
				int i = 0;
				while (i < 100) {
					
					int k = seqService.getSeqByTableName("tbl_docs");
					if(map.containsKey(k)){
						logger.info(JSON.toJSONString("重得的KEY**************"+String.valueOf(k)));
					}else{
						map.put(seqService.getSeqByTableName("tbl_docs"), null);
					}
					i++;
				}
			}
		};
		int runnerCount = 100;
		// Rnner数组，想当于并发多少个。
		TestRunnable[] trs = new TestRunnable[runnerCount];
		for (int i = 0; i < runnerCount; i++) {
			trs[i] = runner;
		}
		// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
		// 开发并发执行数组里定义的内容
		mttr.runTestRunnables();
	}

	/*
	 * @Test public void test2() {
	 * 
	 * logger.info(JSON.toJSONString(seqService.getSeqByTableName("tbl_docs",
	 * 5))); }
	 */

}
