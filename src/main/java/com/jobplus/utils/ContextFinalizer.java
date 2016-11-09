package com.jobplus.utils;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;



/**
 * The web application [] registered the JDBC driver [com.mysql.jdbc.Driver] but failed to unregister it when the web application was stopped. To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
The web application [] appears to have started a thread named [Abandoned connection cleanup thread] but has failed to stop it. This is very likely to create a memory leak
如果热部署的时候不注意以上的信息引起的内存泄露，多次部署后会出现内存溢出OutOfMemory错误
 * 
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Oct 19, 2016 9:15:41 AM <br>
 *
 */
//@WebListener
public class ContextFinalizer implements ServletContextListener {
	protected static final Logger logger = LoggerFactory.getLogger(ContextFinalizer.class);
    public void contextInitialized(ServletContextEvent sce) {
    	logger.info("ContextFinalizer solr.handler.dataimport.scheduler contextInitialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                logger.info(String.format("ContextFinalizer:Driver %s deregistered", d));
            } catch (SQLException ex) {
            	logger.info(String.format("ContextFinalizer:Error deregistering driver %s", d) + ":" + ex);
            }
        }
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
        	logger.info("ContextFinalizer:SEVERE problem cleaning up: " + e.getMessage());
            e.printStackTrace();
        }
    }
}