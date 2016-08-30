package com.baoxue.basejava.reference;

/**
 * Created by baoxue on 16/8/25.
 */
public class C extends B{
    public  static  void  main(String[] args){
        //注释的会报错!
        //B b = new B();
        //C c = (C)b;
        C c = new C();
        B b = (B)c;

    }
}
