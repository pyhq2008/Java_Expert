package com.tingyun.implinterface;


import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Administrator on 2015/5/15.
 */
public class StubPreMain {

    //另外一种入口格式是public static void premain(String agentArgs)
    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException, InterruptedException {
        StubTransformer stubTransformer = new StubTransformer();
        inst.addTransformer(stubTransformer);
        System.out.println("StubPreMain:Add StubTransformer");
    }
}
