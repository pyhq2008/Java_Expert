/**
 * @author: gsw
 * @version: 1.0
 * @CreateTime: 2016年1月8日 下午1:46:45
 * @Description: 无
 */
package org.gongice.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Test {
	private final static int COUNT = 5;

	public static void main(String[] args) {
		// 关闭 c3p0 日志
		Properties p = new Properties(System.getProperties());
		p.put("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
		p.put("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");
		System.setProperties(p);
		// 测试
		testC3p0();
		testDruid();
		testDbcp();
		
	}

	static void testC3p0() {
		Connection conn = null;
		Properties c3p0 = new Properties();
		System.out.println("=======c3p0=======");
		try {
			c3p0.load(Test.class.getClassLoader().getResourceAsStream("c3p0.properties"));
			org.gongice.util.db.c3p0.C3P0ConnectManager.getInstance().setProperties(c3p0);
			org.gongice.util.db.c3p0.C3P0ConnectManager.getInstance().configureDataSourceByProperties();
			for (int i = 1; i <= COUNT; i++) {
				long beginTime = System.currentTimeMillis();
				conn = org.gongice.util.db.c3p0.C3P0ConnectManager.getInstance().getConnection();
				testRunSql(conn);
				conn.close();
				System.out.println("第" + i + "次，耗时："+ (System.currentTimeMillis() - beginTime)+" ms");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void testDruid() {
		Connection conn = null;
		Properties druid = new Properties();
		System.out.println("=======druid=======");
		try {
			druid.load(Test.class.getClassLoader().getResourceAsStream("druid.properties"));
			org.gongice.util.db.druid.DruidConnectManager.getInstance().setProperties(druid);
			org.gongice.util.db.druid.DruidConnectManager.getInstance().configureDataSource();
			for (int i = 1; i <= COUNT; i++) {
				long beginTime = System.currentTimeMillis();
				conn = org.gongice.util.db.druid.DruidConnectManager.getInstance().getConnection();
				testRunSql(conn);
			    conn.close();
				System.out.println("第" + i + "次，耗时："+ (System.currentTimeMillis() - beginTime)+" ms");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static void testDbcp(){
		Connection conn = null;
		Properties dbcp = new Properties();
		System.out.println("=======dbcp=======");
		try {
			dbcp.load(Test.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			org.gongice.util.db.dbcp.DBCPConnectManager.getInstance().setProperties(dbcp);
			org.gongice.util.db.dbcp.DBCPConnectManager.getInstance().configureDataSource();
			for (int i = 1; i <= COUNT; i++) {
				long beginTime = System.currentTimeMillis();
				conn = org.gongice.util.db.dbcp.DBCPConnectManager.getInstance().getConnection();
				testRunSql(conn);
				conn.close();
				System.out.println("第" + i + "次，耗时："+ (System.currentTimeMillis() - beginTime)+" ms");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void testRunSql (Connection conn) throws SQLException{
		String sql ="select * from user";
		DBOperation operation =new DBOperation(conn);
		ResultSet rs =operation.query(sql);
		while (rs.next()) {
			//do nothing
			System.out.println(rs.getString("Name"));
		}
		operation.closeQuery();
	}
}


