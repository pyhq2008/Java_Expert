package com.tingyun.jsonsimple;

import com.newrelic.agent.deps.org.json.simple.JSONArray;
import com.newrelic.agent.deps.org.json.simple.JSONObject;
import com.newrelic.agent.deps.org.json.simple.JSONValue;

/**
 * Created by Administrator on 2016/1/8.
 */
public class TestJSONValue {




    public static final String s="{\"classMatcher\":{\"type\":\"class\",\"rule\":\"com.yun.User\"},\"methodMatchers\":[{\"type\":\"name\",\"rule\":\"addUser\",\"parameters\":[\"int\",\"byte\"]},{\"type\":\"returnType\",\"rule\":\"com.yun.User\",\"parameters\":null},{\"type\":\"annotation\",\"rule\":\"com.spring.RequestMapping\",\"parameters\":null}]}";

    public static final String s2 = "{\"instrumentationCustom\":[{\"classMatcher\":{\"type\":\"class\",\"rule\":\"com.yun.User\"},\"methodMatchers\":[{\"type\":\"name\",\"rule\":\"addUser\",\"parameters\":[\"int\",\"byte\"]},{\"type\":\"returnType\",\"rule\":\"com.yun.User\",\"parameters\":null},{\"type\":\"annotation\",\"rule\":\"com.spring.RequestMapping\",\"parameters\":null}]}],\"hotspots\":[]}";


    public void  testException(){

        Exception e = new RuntimeException("aaaaa");
        System.out.println("I am create a Exception");

    }


    public void test(){
        try {
            TestJSONValue testJSONValue = new TestJSONValue();
            testJSONValue.testException();
        }catch (Exception e){
            System.out.println("I catch it !!");
        }
    }



    public static void  main(String[] args){

        Object object = JSONValue.parse(s2);
        TestJSONValue testJSONValue = new TestJSONValue();
        testJSONValue.test();

        JSONObject jj = (JSONObject)object;
        JSONArray jsonArray = (JSONArray)jj.get("instrumentationCustom");

        JSONObject jsonObject;
        for(Object oj : jsonArray){
            jsonObject = (JSONObject)oj;
            JSONObject classMatcher = (JSONObject)jsonObject.get("classMatcher");
            System.out.println("classMatcher :" );
            System.out.println(classMatcher.get("rule"));
            System.out.println(classMatcher.get("type") );

            JSONArray methodMatchers = (JSONArray)jsonObject.get("methodMatchers");
            JSONObject tmp;
            for(Object o : methodMatchers){
                tmp = (JSONObject)o;
                System.out.println(tmp.get("rule"));
                System.out.println(tmp.get("type"));
                System.out.println(tmp.get("parameters"));
            }
            System.out.println(object);

        }



    }
}
