package com.baoxue.extendclass;

/**
 * Created by Administrator on 2015/5/15.
 */
public class MyClassLoader extends ClassLoader{

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
