package com.baoxue.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class BookOrderProxy implements InvocationHandler {

    private Object target;

    public BookOrderProxy(Object target)
    {
        this.target = target;
    }

    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
    {
        //在调用具体目标对象的方法之前，执行一些处理，
        System.out.println("before order  ......");
        //调用具体目标对象的方法
        Object result = method.invoke(target, args);
        //在调用具体目标对象之后，执行一些处理
        System.out.println("after order  ......");
        return result;
    }
}