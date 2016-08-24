package com.tingyun.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestSSLContext {

    public static SSLContext getSSLContext() throws Exception
    {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream input = new FileInputStream("d:/tmp/trust2.jks");
        ks.load(input, "123456".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        SSLContext context = SSLContext.getInstance("TLSv1.2");
        //这里只指定了受信任的证书（单向认证），如果是双向认证的话，则第一个参数不能为null
        context.init(null, tmf.getTrustManagers(), null);
        return context;
    }
    public static void main(String[] args) throws Exception
    {
        HttpClientBuilder builder = HttpClients.custom();
        builder.setSSLContext(getSSLContext());

        CloseableHttpClient httpclient = builder.build();
        HttpGet httpget = new HttpGet("https://127.0.0.1:4488");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println(body);
        response.close();
        httpclient.close();
    }
}
