package com.tingyun.httpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/3/30.
 */
public class TestHttpAsyncClient {

    public static void test2() throws InterruptedException, ExecutionException,
            IOException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        // Start the client
        httpclient.start();

        // Execute 100 request in async
        final HttpGet request = new HttpGet(
                "http://www.baidu.com");
        request.setHeader("Connection", "close");
        List<Future<HttpResponse>> respList = new LinkedList<Future<HttpResponse>>();
        for (int i = 0; i < 50; i++) {
            respList.add(httpclient.execute(request, null));
        }

        // Print response code
        for (Future<HttpResponse> response : respList) {
            response.get().getStatusLine();
            // System.out.println(response.get().getStatusLine());
        }

        httpclient.close();
    }


    public static void test1() throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // Execute 500 request in async
        for (int i = 0; i < 50; i++) {
            HttpGet request = new HttpGet(
                    "http://www.baidu.com");
            request.setHeader("Connection", "close");
            CloseableHttpResponse response = httpclient.execute(request);
            // System.out.println(response.getStatusLine());
            response.getStatusLine();
            response.close();
        }

        httpclient.close();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        test1();
        System.out.println(System.currentTimeMillis() - start);
        long start2 = System.currentTimeMillis();
        test2();
        System.out.println(System.currentTimeMillis() - start2);



    }
}
