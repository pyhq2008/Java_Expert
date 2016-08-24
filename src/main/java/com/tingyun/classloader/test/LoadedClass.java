package com.tingyun.classloader.test;

/**
 * Created by Administrator on 2015/11/24.
 */
public class LoadedClass {

    public LoadedClass() {
        System.out.println("LoadedClass is loaded by: "
                + this.getClass().getClassLoader());

    }
}
