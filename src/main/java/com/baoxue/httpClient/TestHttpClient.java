package com.baoxue.httpClient;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * Created by Administrator on 2015/8/3.
 */
public class TestHttpClient {



    public static void  main(String[] args){
        TestHttpClient testHttpClient = new TestHttpClient();

        for(int i = 0;i<10;i++){
            ConcurrentThread concurrentThread = new ConcurrentThread(testHttpClient);
            concurrentThread.start();
        }


    }



    public void  testHttpClient(){

        HttpClient httpClient = new HttpClient();
        //创建GET方法的实例
        GetMethod getMethod = new GetMethod("http://localhost:8080/strutsspring/searchEntry.do");
        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            //读取内容
            byte[] responseBody = getMethod.getResponseBody();
            //处理内容
//            System.out.println(new String(responseBody));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            getMethod.releaseConnection();
        }
    }
}


