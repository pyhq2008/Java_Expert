/**
 * @author: gsw
 * @version: 1.0
 * @CreateTime: 2016年1月8日 上午9:30:03
 * @Description: 无
 */
package org.gongice.util.db.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DBCPConnectManager {
	// 单例
	private final static DBCPConnectManager INSTANCE = new DBCPConnectManager();
	public static final DBCPConnectManager getInstance() { return INSTANCE;}
	// 配置文件
	private Properties properties;
	// 数据源
	private BasicDataSource dataSource;
	/**
	 * 初始化配置文件
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * 配置数据源
	 */
	public final void configureDataSource() {
		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得连接
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
