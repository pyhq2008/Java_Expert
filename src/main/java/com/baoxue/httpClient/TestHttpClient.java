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
        //����GET������ʵ��
        GetMethod getMethod = new GetMethod("http://localhost:8080/strutsspring/searchEntry.do");
        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            //ִ��getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            //��ȡ����
            byte[] responseBody = getMethod.getResponseBody();
            //��������
//            System.out.println(new String(responseBody));
        } catch (HttpException e) {
            //�����������쳣��������Э�鲻�Ի��߷��ص�����������
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //���������쳣
            e.printStackTrace();
        } finally {
            //�ͷ�����
            getMethod.releaseConnection();
        }
    }
}


