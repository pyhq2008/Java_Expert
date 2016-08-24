package com.tingyun.AsyncHttpClient;

import com.ning.http.client.*;
import com.ning.http.client.multipart.FilePart;
import com.ning.http.client.multipart.Part;
import com.ning.http.client.multipart.StringPart;
import org.apache.http.HttpException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/3/21.
 */
public class AsyncHttpClientWapperImpl {

    private AsyncHttpClient client;

    public AsyncHttpClientWapperImpl(AsyncHttpClientConfig.Builder xxbuilder) {
        client = new AsyncHttpClient(xxbuilder.build());
    }

    // 专门针对xx服务器的连接管理对象
    // 因为不同服务可能超时等参数不用，所以针对不同服务，把连接管理对象区分开来，这只是其中一个
    private static AsyncHttpClientConfig.Builder xxbuilder = new AsyncHttpClientConfig.Builder();
    static {
        xxbuilder.setConnectTimeout(3000);  // 连接超时
        xxbuilder.setReadTimeout(2000);     // 读取数据超时
        xxbuilder.setMaxConnections(1000);  // 最大连接数
    }
    /*
     * 返回针对XX服务的httpClient包装类
     */
    public static AsyncHttpClientWapperImpl getXXSearchHttpClient() {
        return new AsyncHttpClientWapperImpl(xxbuilder);
    }

    public byte[] getWithQueryURL(String queryURL)
            throws HttpClientException {
        if(queryURL == null) {
            throw new HttpClientException("queryURL为空");
        }
        byte[] newbuf = executeByGet(queryURL);
        if ((newbuf == null) || (newbuf.length == 0)) {
            throw new HttpClientException("Server response is null: " + queryURL);
        }
        return newbuf;
    }
    private byte[] executeByGet(String queryURL) throws HttpClientException {
        byte[] responseBody = null;
        try {
            Future<Response> f = client.prepareGet(queryURL).execute();
            responseBody = getBytesFromInpuStream(f.get().getResponseBodyAsStream());
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
        return responseBody;
    }

    public byte[] postWithParamsMap(String queryURL,
                                    Map<String, String> paramsMap) throws HttpClientException {
        if(queryURL == null) {
            throw new HttpClientException("queryURL为空");
        }
        byte[] newbuf = executeByPostByParamMap(queryURL, paramsMap);
        if ((newbuf == null) || (newbuf.length == 0)) {
            throw new HttpClientException("Server response is null: " + queryURL);
        }
        return newbuf;
    }
    private byte[] executeByPostByParamMap(String queryURL,Map<String,String> paramsMap) throws HttpClientException {
        byte[] responseBody = null;
        try {
            RequestBuilder requestBuilder = new RequestBuilder();
            // 添加 key-value参数
            if(paramsMap != null && paramsMap.size() > 0) {
                Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
                while(iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    if(entry.getKey() != null) {
                        requestBuilder.addFormParam(entry.getKey(), entry.getValue());
                    }
                }
            }
            // 添加RequestHeader，key
            requestBuilder.addHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            requestBuilder.setMethod("POST");
            // 添加URL
            requestBuilder.setUrl(queryURL);
            // request
            Request request = requestBuilder.build();
            // 提交
            ListenableFuture<Response> f = client.executeRequest(request);
            responseBody = getBytesFromInpuStream(f.get().getResponseBodyAsStream());
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
        return responseBody;
    }



    public byte[] postWithBytes(String queryURL, byte[] bytes)
            throws HttpClientException {
        if(queryURL == null) {
            throw new HttpClientException("queryURL is null.");
        }
        byte[] newbuf = executeByPostWithBytes(queryURL, bytes);
        if ((newbuf == null) || (newbuf.length == 0)) {
            throw new HttpClientException("Server response is null: " + queryURL);
        }
        return newbuf;
    }


    private byte[] executeByPostWithBytes(String queryURL, byte[] bytes) throws HttpClientException {
        byte[] responseBody = null;
        try {
            RequestBuilder requestBuilder = new RequestBuilder();
            // 添加 bytes参数
            requestBuilder.setBody(bytes);
            // 添加RequestHeader，key
            requestBuilder.addHeader("Content-type", "text/plain; charset=UTF-8");
            requestBuilder.setMethod("POST");
            // 添加URL
            requestBuilder.setUrl(queryURL);
            // request
            Request request = requestBuilder.build();
            // 提交
            ListenableFuture<Response> f = client.executeRequest(request);
            responseBody = getBytesFromInpuStream(f.get().getResponseBodyAsStream());
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
        return responseBody;
    }


    public byte[] postWithFileListAndParamMap(String queryURL,
                                              List<File> fileList, Map<String, String> paramMap)
            throws HttpClientException, HttpException, IOException {
        if(queryURL == null) {
            throw new HttpClientException("queryURL is null.");
        }
        if(fileList == null || fileList.size() == 0) {
            throw new HttpClientException("fileList is null.");
        }
        if(paramMap == null || paramMap.size() == 0) {
            throw new HttpClientException("paramMap is null.");
        }
        return executeByPostWithFileListAndParamMap(queryURL, fileList, paramMap);
    }
    private byte[] executeByPostWithFileListAndParamMap (String queryURL,List<File> fileList,Map<String,String> paramsMap) throws HttpException, IOException, HttpClientException {
        if(queryURL != null && fileList != null && fileList.size() > 0) {
            byte[] responseBody = null;
            try {
                RequestBuilder requestBuilder = new RequestBuilder();
                // FilePart
                for(File file : fileList){
                    Part filePart = new FilePart(file.getName(),file);
                    requestBuilder.addBodyPart(filePart);
                }
                // StringPart
                if(paramsMap != null ) {
                    Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
                    Iterator<Map.Entry<String, String>> it = entrySet.iterator();
                    while(it.hasNext()) {
                        Map.Entry<String, String> entry = it.next();
                        Part stringPart = new StringPart(entry.getKey(),entry.getValue());
                        requestBuilder.addBodyPart(stringPart);
                    }
                }
                // 添加RequestHeader，key
                requestBuilder.addHeader("Content-type", "multipart/form-data; charset=UTF-8");
                requestBuilder.setMethod("POST");
                // 添加URL
                requestBuilder.setUrl(queryURL);
                // request
                Request request = requestBuilder.build();
                // 提交
                ListenableFuture<Response> f = client.executeRequest(request);
                responseBody = getBytesFromInpuStream(f.get().getResponseBodyAsStream());
            } catch (Exception e) {
                throw new HttpClientException(e);
            }
            return responseBody;
        }
        return null;
    }

    private byte[] getBytesFromInpuStream(InputStream instream) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        try {
            int length;
            byte[] tmp = new byte[8096];
            while ((length = instream.read(tmp)) != -1) {
                outstream.write(tmp, 0, length);
            }
            return outstream.toByteArray();
        } finally {
            instream.close();
            outstream.close();
        }
    }



    public static void main(String[] args) throws HttpClientException {
        AsyncHttpClientWapperImpl asyncHttpClientWapper = getXXSearchHttpClient();
        byte[] bytes = asyncHttpClientWapper.executeByGet("www.baidu.com");


    }


}
