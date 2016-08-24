package com.baoxue.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/4/8.
 */
public class Test {

    private static String getFirstResult(String question, List<String> engines) {
        AtomicReference<String> result = new AtomicReference<String>();
        for(String base: engines) {
            String url = base + question;
//            new Thread(() -> {
//                result.compareAndSet(null, WS.url(url).get());
//            }).start();
        }
        while(result.get() == null); // wait for some result to appear
        return result.get();
    }


    private static String getFirstResultExecutors(String question, List<String> engines) {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(4));

        for(String base: engines) {
            String url = base + question;
//            service.submit(() -> {
////                return WS.url(url).get();
//            });

        }
        try {
            return service.take().get();
        }
         catch (InterruptedException e) {
            e.printStackTrace();
             return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        String ss = "/abac/aaa/aa?a=1";
        System.out.print(ss.substring(0,ss.indexOf("?")));
    }

}
