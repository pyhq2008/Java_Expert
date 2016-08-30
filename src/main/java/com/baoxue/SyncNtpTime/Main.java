package com.baoxue.SyncNtpTime;


import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/8.
 */
public class Main {

    @Test
    public void test01() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        HttpHead httpHead = new HttpHead("http://ntpdev.tingyun.com/time");
        httpHead.addHeader("X-Timestamp", String.valueOf(System.currentTimeMillis()));
        long t1  = Long.parseLong(httpHead.getFirstHeader("X-Timestamp").getValue());
        System.out.println(t1);

        response =  httpClient.execute(httpHead);
        long t2 = System.currentTimeMillis();
        System.out.println(t2);
        try {
             String T1str = response.getFirstHeader("X-Timestamp-Recv").getValue();
             String T2str = response.getFirstHeader("X-Timestamp-Sent").getValue();

            System.out.println(T1str);
            System.out.println(T2str);

            long T1 = Long.parseLong(T1str.replace(".",""));
            long T2 = Long.parseLong(T2str.replace(".",""));
            long  time_offset = ((T1+T2)-(t1 + t2))/2;

            System.out.println(time_offset/1000);

            BigDecimal bd = new BigDecimal(System.currentTimeMillis());
            System.out.println(bd.divide(new BigDecimal(1000L)));

            System.out.println(new BigDecimal(6000.831).longValue());



        } finally {
            httpClient.close();
            response.close();
        }
    }



    @Test
    public void test02() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        HttpEntity entity = null;
        HttpHead httpHead = new HttpHead("http://www.baidu.com/");
        httpHead.addHeader("X-Timestamp",String.valueOf(System.currentTimeMillis()));
        response =  httpClient.execute(httpHead);
        entity = response.getEntity();
        System.out.println(entity);
        try {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());                           //����������״̬
            Header[] headers = response.getAllHeaders();                    //���ص�HTTPͷ��Ϣ
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }
            System.out.println("----------------------------------------");
            String responseString = null;
            if (response.getEntity() != null) {
                responseString = EntityUtils.toString(response.getEntity());         //���ط�������Ӧ��HTML����
                System.out.println(responseString);                                   //��ӡ����������Ӧ��HTML����
            }
        } finally {
            if (entity != null)
                entity.consumeContent();//release connection gracefully
        }
    }




    @Test
    public void testHeadMethod() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("X-Timestamp-Recv",String.valueOf(System.currentTimeMillis()));
        response.addHeader("X-Timestamp-Sent",String.valueOf(System.currentTimeMillis()));
        response.addHeader("cookie", "path=/ray;domain=localhost:3306");
        System.out.println(response.getFirstHeader("X-Timestamp-Recv"));
        System.out.println(response.getFirstHeader("X-Timestamp-Sent"));
        System.out.println(response.getFirstHeader("cookie"));
        System.out.println(response.getLastHeader("cookie"));
        System.out.println("---------------------------------------------");

        Header[] heads = response.getHeaders("cookie");
        for(Header head : heads)
            System.out.println(head);
        System.out.println("---------------------------------------------");

        HeaderIterator it = response.headerIterator();
        while(it.hasNext())
            System.out.println(it.next());
        System.out.println("---------------------------------------------");

        HeaderElementIterator hei = new BasicHeaderElementIterator(response.headerIterator());
        while(hei.hasNext()) {
            HeaderElement element = hei.nextElement();
            System.out.println(element.getName() + "=" + element.getValue());
            NameValuePair[] params = element.getParameters();
            for(NameValuePair name : params)
                System.out.println(name);
        }
    }


    @Test
    public void testScheduleTask(){
      String s = "org/apache";
        System.out.println(s.replace(".","/"));


    }


    @Test
    public void testEntiy() throws IOException {
        StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));

        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);
    }

    public static void main(String[] args) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();      //ʵ����һ��HttpClient
        HttpResponse response = null;
        HttpEntity entity = null;
        httpclient.getParams().setParameter(
                ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
        HttpPost httpost = new HttpPost("http://www.baidu.com/");           //�����еĲ����ǣ�servlet�ĵ�ַ
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("jqm", "fb1f7cbdaf2bf0a9cb5d43736492640e0c4c0cd0232da9de"));
//        //   BasicNameValuePair("name", "value"), name��post�����������, value�Ǵ���Ĳ���ֵ
//        nvps.add(new BasicNameValuePair("sqm", "1bb5b5b45915c8"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));            //����������post������
        response =  httpclient.execute(httpost);                                               //ִ��
        entity = response.getEntity();                                                             //���ط�������Ӧ
        try {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());                           //����������״̬
            Header[] headers = response.getAllHeaders();                    //���ص�HTTPͷ��Ϣ
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i]);
            }
            System.out.println("----------------------------------------");
            String responseString = null;
            if (response.getEntity() != null) {
                responseString = EntityUtils.toString(response.getEntity());         //���ط�������Ӧ��HTML����
                System.out.println(responseString);                                   //��ӡ����������Ӧ��HTML����
            }
        } finally {
            if (entity != null)
                entity.consumeContent();                                                   // release connection gracefully
        }
        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();

        }
    }


//    @Test
//    public void  testHttpContext() throws IOException {
//        HttpClientContext context = HttpClientContext.create();
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(1000)
//                .setConnectTimeout(1000)
//                .build();
//
//        HttpGet httpget1 = new HttpGet("http://www.baidu.com/");
//        httpget1.setConfig(requestConfig);
//        CloseableHttpResponse response1 = httpclient.execute(httpget1, (HttpContext) context);
//        try {
//            HttpEntity entity1 = response1.getEntity();
//        } finally {
//            response1.close();
//        }
//        HttpGet httpget2 = new HttpGet("http://localhost/2");
//        CloseableHttpResponse response2 = httpclient.execute(httpget2, (HttpContext) context);
//        try {
//            HttpEntity entity2 = response2.getEntity();
//        } finally {
//            response2.close();
//        }
//    }
}
