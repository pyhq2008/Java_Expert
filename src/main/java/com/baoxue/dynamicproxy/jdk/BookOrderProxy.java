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
        //�ڵ��þ���Ŀ�����ķ���֮ǰ��ִ��һЩ����
        System.out.println("before order  ......");
        //���þ���Ŀ�����ķ���
        Object result = method.invoke(target, args);
        //�ڵ��þ���Ŀ�����֮��ִ��һЩ����
        System.out.println("after order  ......");
        return result;
    }
}