package com.jobplus.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 修復BasicDataSource类close()的一个Bug。 关闭BasicDataSource时不能注销JDBC驱动程序，导致tomat内存泄漏。
 * 
 * 
 * Closing BasicDataSource doesn't deregister JDBC driver, causing memory leak
 * 
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Sep 19, 2016 9:32:03 AM <br>
 *
 */
public class JobplusBasicDataSource extends BasicDataSource {

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		throw new SQLException("BasicDataSource is not a wrapper.");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void close() throws SQLException {
		DriverManager.deregisterDriver(DriverManager.getDriver(url));
		super.close();
	}

}