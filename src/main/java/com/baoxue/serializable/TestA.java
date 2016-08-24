package com.baoxue.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/21.
 */
public class TestA implements Serializable {


    private Map<String,TestB> map = new HashMap<String, TestB>();

    public void setMap(Map<String,TestB> map){
        this.map = map;
    }

    public static void main(String[] args){
        TestA testA = new TestA();
        Map<String,TestB> map1 = new HashMap<String, TestB>();
        TestB testB1 = new TestB();
        TestB testB2 = new TestB();
        map1.put("a",testB1);
        map1.put("b",testB2);
        testA.setMap(map1);
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(oStream).writeObject(map1);
            oStream.close();
            System.out.println(oStream.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
