package com.baoxue.addtime;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Administrator on 2015/5/15.
 */
public class StubPreMain {

    //����һ����ڸ�ʽ��public static void premain(String agentArgs)
    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException, InterruptedException {
        StubTransformer stubTransformer = new StubTransformer();
        inst.addTransformer(stubTransformer);


//        Class[] classes = inst.getAllLoadedClasses();
//        System.out.println(classes.length);
////        for(Class clas : classes){
////            System.out.println(clas.getName());
////        }
        System.out.println("StubPreMain:Add StubTransformer");
    }
}
