package com.tingyun.database.mysql;

import com.tingyun.util.Invoker;

import java.sql.*;

/**
 * Created by Administrator on 2015/6/26.
 */
public class TestMysql {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://192.168.2.130:3306/fvt";
        String user = "root";
        String password = "nbs2o13";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            System.out.println(conn.getClass().getCanonicalName().replace(".","\\"));
            System.out.println(conn.getClass().getName());

//            System.out.println(conn.getSchema());
//            System.out.println(conn.getClientInfo());
////            System.out.println(conn.getClass().getMethod("getHost").invoke(conn, new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getSchema").invoke(conn,new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getURL").invoke(conn,new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getUser").invoke(conn,new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getServerMajorVersion").invoke(conn,new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getServerMinorVersion").invoke(conn,new Object[0]));
////            System.out.println(conn.getClass().getSuperclass().getDeclaredMethod("getServerSubMinorVersion").invoke(conn,new Object[0]));

            System.out.println(Invoker.invokeMethod(conn, "getHost", new Class[0], new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getSchema",new Class[0],new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getURL",new Class[0],new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getUser",new Class[0],new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getServerMajorVersion",new Class[0],new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getServerMinorVersion",new Class[0],new Object[0]));
            System.out.println(Invoker.invokeMethod(conn,"getServerSubMinorVersion",new Class[0],new Object[0]));
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
