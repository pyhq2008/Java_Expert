package com.tingyun.database.pgsql;

import com.tingyun.util.Invoker;

import java.sql.*;

/**
 * Created by Administrator on 2015/7/16.
 */
public class TestPgSql {

    public static void main(String[] args){
        try
        {
            Class.forName("org.postgresql.Driver").newInstance();
            String url="jdbc:postgresql://192.168.2.130:5432/javatest";
            String user="postgres";
            String pwd="nbs2o13";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String url2 = databaseMetaData.getURL();
            System.out.println((String) Invoker.getFieldValue(conn, "creatingURL"));
            System.out.println((String)Invoker.getFieldValue(conn,"dbVersionNumber"));

            Statement st =conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM test");
            while (rs.next())
            {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
