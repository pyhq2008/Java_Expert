package com.baoxue.util;

import org.objectweb.asm.Type;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/7/23.
 */
public class TestSysProperty {

    public static void main(String[] args){

        System.out.println(System.getProperty("catalina.home"));

        System.out.println(System.getProperty("CATALINA_HOME"));

        System.out.println(Type.getType(java.lang.reflect.InvocationHandler[].class));

        String urlRules = "[{\"replace_all\":false,\"match_expression\":\".*\\.(ace|arj|ini|txt|udl|plist|css|gif|ico|jpe?g|js|png|swf|woff|caf|aiff|m4v|mpe?g|mp3|mp4|mov)$\",\"terminate_chain\":true,\"ignore\":false,\"each_segment\":false,\"replacement\":\"/*.\\1\",\"eval_order\":1000},{\"replace_all\":false,\"match_expression\":\"^[0-9][0-9a-f_,.-]*$\",\"terminate_chain\":false,\"ignore\":false,\"each_segment\":true,\"replacement\":\"*\",\"eval_order\":1001},{\"replace_all\":false,\"match_expression\":\"^(.*)/[0-9][0-9a-f_,-]*\\.([0-9a-z][0-9a-z]*)$\",\"terminate_chain\":false,\"ignore\":false,\"each_segment\":false,\"replacement\":\"\\1/.*\\2\",\"eval_order\":1002}]";

//        System.out.println(urlRules.replaceFirst("\"ignore\":false,", "\"ignore\":true,"));

        Runnable runnable = new Runnable() {
            public void run() {
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                for(StackTraceElement stackTraceElement : stackTraceElements){
                    System.out.println("className:" + stackTraceElement.getClassName() + " | methodName:" + stackTraceElement.getMethodName());
                }
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(runnable);


        Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    public void run() {
                        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                        for (StackTraceElement stackTraceElement : stackTraceElements) {
                            System.out.println("className:" + stackTraceElement.getClassName() + " | methodName:" + stackTraceElement.getMethodName());
                        }
                    }
                }, 0, 5 * 60 * 1000);
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        System.out.println(stackTraceElements);

    }
}
