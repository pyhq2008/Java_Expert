package com.baoxue.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/3/23.
 */
public class CglibProxy  implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        //������Ҫ�����������
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //ͨ���ֽ��뼼����̬��������ʵ��
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("brefore method .....");
        //ͨ����������ø����еķ���
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("after method ......");
        return result;
    }
}
