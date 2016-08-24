package com.baoxue.database.oracle;

import com.baoxue.util.Invoker;

import java.sql.*;

/**
 * Created by Administrator on 2015/7/16.
 */
public class TestOracle {


    public static void main(String[] args){

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String url="jdbc:oracle:thin:@192.168.1.15:1521:nbsdb";
        //10.10.10.10为oracle数据库服务器的IP
        //ora9i为oracle数据库的service Name
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, "xiaoren", "xiaoren");
            stat = conn.createStatement();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            String dburl = (String)Invoker.getFieldValue(conn,"url");
            System.out.println(dburl);
            System.out.println((String)Invoker.getFieldValue(conn,"database"));
            System.out.println((String)Invoker.getFieldValue(conn,"proto"));
            System.out.println((String)Invoker.getFieldValue(conn,"versionNumber"));
            rs = stat.executeQuery("select * from dual");
            while (rs.next()) {
                System.out.println(rs.getFetchSize());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                if (rs !=null) {
                    rs.close();
                    rs = null;
                }
                if (stat !=null) {
                    stat.close();
                    stat = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
