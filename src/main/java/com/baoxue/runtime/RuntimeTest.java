package com.baoxue.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RuntimeTest {


    public static void main(String[] args) throws Exception {
        Runtime rt = Runtime.getRuntime();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {

            Process p = rt.exec("ipconfig");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
            br.close();
            throw new IOException();

        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.await();

    }
}
