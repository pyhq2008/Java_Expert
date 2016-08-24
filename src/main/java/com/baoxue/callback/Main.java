package com.baoxue.callback;

/**
 * Created by Administrator on 2015/7/3.
 */
public class Main {

    public static void main(String[] args) {
        new TestCallBack().compute(1000, new ComputeCallBack() {

            public void onComputeEnd() {
                System.out.println("end back!!!");

            }
        });
    }
}
