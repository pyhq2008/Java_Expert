/** 
 * @author: gsw
 * @version: 1.0
 * @CreateTime: 2016年1月8日 上午9:30:03
 * @Description: 无
 */
package org.gongice.util.db.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DruidConnectManager {
	// 单例
	private final static DruidConnectManager INSTANCE=new DruidConnectManager();
	public static final DruidConnectManager getInstance() {return INSTANCE;}
	// 配置文件
	private Properties properties;
	// 数据源
	private DataSource dataSource;
	
	/**
	 * 初始化配置文件
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * 配置数据源
	 * @throws Exception
	 */
	public final void configureDataSource() throws Exception {
		dataSource = DruidDataSourceFactory.createDataSource(properties);
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}


//java应用
			/*confile = DataSourceUtil.class.getClassLoader().getResource("")
					.getPath()
					+ confile;
			System.out.println("配置文件:" + confile);
			File file = new File(confile);
			inputStream = new BufferedInputStream(new FileInputStream(file));*/
			
