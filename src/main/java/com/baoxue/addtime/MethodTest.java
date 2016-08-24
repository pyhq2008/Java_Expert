package com.baoxue.addtime;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Created by Administrator on 2015/5/15.
 */
public class MethodTest{
    public void hello() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) throws IOException {
//        MethodTest methodTest = new MethodTest();
//        methodTest.hello();
//        System.out.println(MethodTest.class.getProtectionDomain().getCodeSource().getLocation());
//        URL url = new URL("file:///E:\\java%e6%8e%a2%e9%92%88%e8%b5%84%e6%96%99\\V1.1.0_20150521\\target\\tingyun-agent-java.jar");
//        JarFile jarFile = new JarFile(URLDecoder.decode(url.getFile(), "UTF-8"));
//        for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
//            JarEntry jarEntry = entries.nextElement();
//            System.out.println(jarEntry.getName());
//        }


        String runtimeName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(runtimeName);
        String[] split = runtimeName.split("@");

    }

}
