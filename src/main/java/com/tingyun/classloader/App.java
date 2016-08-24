package com.tingyun.classloader;

import java.io.File;

/**
 * Created by Administrator on 2015/9/10.
 */
public class App {

    public static void main(String[] args){
        Object classLoader = ClassLoader.class.getSimpleName();

        System.out.println("CLASSLOADER" == "CLASSLOADER");

        File file  = new File("");
    }
}
