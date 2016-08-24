package com.tingyun.database.db2;

import com.tingyun.util.Invoker;

import java.sql.*;

/**
 * Created by Administrator on 2015/7/24.
 */
public class TestDB2 {

    public static void main(String[] args) {
        String driver = "com.ibm.db2.jcc.DB2Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:db2://192.168.2.130:50000/sample";
        String user = "db2inst1";
        String password = "nbs2o13";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            System.out.println(url2);
            System.out.println(conn.getClass().getCanonicalName().replace(".","\\"));
            System.out.println(conn.getClass().getName());
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            Statement statement = conn.createStatement();
            String sql = "select * from dept";
            ResultSet rs = statement.executeQuery(sql);
//            while(rs.next()){
//                System.out.println(rs.getString("Name"));
//            }



        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
