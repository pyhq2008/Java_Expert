package com.tingyun.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 * Created by Administrator on 2015/6/24.
 */
public class App {


    public static void main(String[] args){
//        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
//        Session session = cluster.connect("pimin_net");
//        String cql = "select * from pimin_net.users;";
//        ResultSet resultSet =  session.execute(cql);
//        System.out.println(resultSet.all().size());

        String ss = "asdfsdfsdf|";
        System.out.print(ss.substring(0, ss.indexOf("|")));

    }
}
