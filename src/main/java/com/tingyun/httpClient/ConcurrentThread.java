package com.tingyun.httpClient;

/**
 * Created by Administrator on 2015/8/27.
 */
public class ConcurrentThread extends Thread  {


    private TestHttpClient testHttpClient;

        public ConcurrentThread(TestHttpClient testHttpClient) {
            this.testHttpClient = testHttpClient;
        }

        public void run() {
            try {
                for (int i = 0; i<10000;i++){
                    testHttpClient.testHttpClient();
                }

            } catch (Exception e) {
            }
        }

}
