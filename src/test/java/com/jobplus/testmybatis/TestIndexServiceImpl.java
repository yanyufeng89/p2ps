package com.jobplus.testmybatis;

import com.jobplus.service.impl.IndexServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类

@ContextConfiguration(locations = {"classpath:spring-application.xml"})
public class TestIndexServiceImpl {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(TestIndexServiceImpl.class);
    @Resource
    private IndexServiceImpl indexService;

    @Test
    public void test1() {
        List list = indexService.getLatestShareData();
        List list1 = indexService.getHotRecommData();
        List list2 = indexService.getHotShareData();
        System.out.println(list);
        System.out.println("-----------------------");
        System.out.println(list1);
        System.out.println("-----------------------");
        System.out.println(list2);
    }
}