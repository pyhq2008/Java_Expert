package com.tingyun.util;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ClassBase {

    public void test1(){
        System.out.println("I am test1 executed .....");
    }



    public static void  main(String[] args){
        ClassBase classBase = new ClassBase(){
          public void test1(){
              System.out.println("I am new  test1 executed .....");
          }
        };

        classBase.test1();
    }
}
