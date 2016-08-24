/**
 * @author: gsw
 * @version: 1.0
 * @CreateTime: 2016年1月7日 下午3:42:09
 * @Description: 无
 */
package org.gongice.util.db.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.gongice.util.db.DBTYPE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class C3P0ConnectManager {
	// 使用单利模式创建数据库连接池
	private final static C3P0ConnectManager INSTANCE = new C3P0ConnectManager();
	public static final C3P0ConnectManager getInstance() {return INSTANCE;}
	// 配置文件
	private Properties properties;
	// 数据源
	private ComboPooledDataSource dataSource;
	/**
	 * 初始化数据源
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * 配置数据源
	 * 不需要显式加载数据源配置
	 * 当默认无参数的构造方法 new ComboPooledDataSource()时。使用c3p0-config.xml 中的 <default-config>
	 * @throws Exception
	 */
	public final void configureDataSource() throws Exception {
		dataSource = new ComboPooledDataSource();
	}
	
	/**
	 * 配置数据源
	 * 不需要显式加载数据源配置
	 * 使用有参数的构造方法 new ComboPooledDataSource("gbase") 时。使用c3p0-config.xml 中的 <name-config>
	 * @throws Exception
	 */
	public final void configureDataSource(DBTYPE type) {
		dataSource = null;
		switch (type) {
		case GBASE:
			dataSource = new ComboPooledDataSource("gbase");
			break;
		case DB2:
			dataSource = new ComboPooledDataSource("db2");
			break;
		case ORACLE:
			dataSource = new ComboPooledDataSource("oracle");
			break;
		default:
			dataSource = new ComboPooledDataSource();
		}
	}
	
	/**
	 * 配置数据源
	 * 显式设置数据源参数
	 * @throws Exception
	 */
	public final void configureDataSourceByProperties() throws Exception {
		dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(properties.getProperty("driverClass"));
		dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
		dataSource.setUser(properties.getProperty("user"));
		dataSource.setPassword(properties.getProperty("password"));
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