package com.baoxue.database.sqlserver;

import com.baoxue.util.Invoker;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Administrator on 2015/7/16.
 */
public class TestSQLServer {

    public static void  main(String[] args){
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        // URLָ��Ҫ���ʵ����ݿ���scutcs
        String url = "jdbc:sqlserver://192.168.2.200:1433;databaseName=Test";
        String user = "sa";
        String password = "123456";
//        String url = "jdbc:sqlserver://192.168.2.130:1433;databaseName=dataaccuracy";
//        String user = "sa";
//        String password = "nbs2o13";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            Properties properties = (Properties)
                    Invoker.getFieldValue(conn, "activeConnectionProperties");
            System.out.println(properties.getProperty("serverName"));
            System.out.println(properties.getProperty("databaseName"));
            System.out.println(properties.getProperty("portNumber"));
            System.out.println(properties.getProperty("user"));
            System.out.println(Invoker.getFieldValue(conn, "sqlServerVersion"));
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
