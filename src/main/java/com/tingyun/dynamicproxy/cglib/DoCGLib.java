package com.tingyun.dynamicproxy.cglib;

/**
 * Created by Administrator on 2016/3/23.
 */
public class DoCGLib {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //ͨ����������ķ�ʽ����������
        SayHello proxyImp = (SayHello)proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}
