package com.baoxue.database.derby;

import java.sql.*;

/**
 * Created by Administrator on 2015/7/24.
 */
public class TestDerby {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbededDriver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:derby://localhost:1527/sample;create=true";
        String user = "db2inst1";
        String password = "nbs2o13";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            System.out.println(conn.getClass().getCanonicalName().replace(".","\\"));
            System.out.println(conn.getClass().getName());
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            Statement statement = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = statement.executeQuery(sql);
//            while(rs.next()){
//                System.out.println(rs.getString("Name"));
//            }



        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
