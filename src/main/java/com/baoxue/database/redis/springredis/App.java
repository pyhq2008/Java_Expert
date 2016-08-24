package com.baoxue.database.redis.springredis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/3.
 */
public class App {

    public  static  void  main(String[] args){

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        Order order = new Order("adsfasdfsdf","asdfasdfsf111",12.32,new Date());
        OrderDao orderDao =(OrderDao)ctx.getBean("orderDao");
        orderDao.save(order);


    }
}
