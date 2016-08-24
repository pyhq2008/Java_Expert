package com.baoxue.brower;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/9/18.
 */
public class PatterTest
{
    public static void main(String[] args){
      Pattern HEAD_PATTERN = Pattern.compile("<head(>)|(\\s+[^>]*>)", 34);

//        Pattern HEAD_PATTERN = Pattern.compile("<head[^>]*>", 34);
//        Pattern HEAD_PATTERN = Pattern.compile("(<head\\s+[^>]*>)|(<head>)", 34);
        Matcher matcher =  HEAD_PATTERN.matcher("<head>");
        System.out.println(matcher.find());
        System.out.println(matcher.start());
        matcher =  HEAD_PATTERN.matcher("<Head>");
        System.out.println(matcher.find());
        matcher =  HEAD_PATTERN.matcher("shEAd>");
        System.out.println(matcher.find());
        matcher =  HEAD_PATTERN.matcher("<head                   id=>");
        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        matcher =  HEAD_PATTERN.matcher("<headxxx>");
        System.out.println(matcher.find());


    }
}
