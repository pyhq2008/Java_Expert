package com.baoxue.callback;

/**
 * Created by Administrator on 2015/7/3.
 */
public class TestCallBack {

    public void compute(int n, ComputeCallBack callback) {
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        callback.onComputeEnd();
    }
}
