package com.baoxue.SyncNtpTime;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Administrator on 2015/6/9.
 */
public class LoaderSample2 {

    public static void main(String[] args) {
        try {
            String path = System.getProperty("user.dir");
            URL[] us = {new URL("file://" + path + "/sub/")};
            ClassLoader loader = new URLClassLoader(us);
            Class c = loader.loadClass("LoaderSample3");
            Object o = c.newInstance();
            Field f = c.getField("age");
            int age = f.getInt(o);
            System.out.println("age is " + age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
