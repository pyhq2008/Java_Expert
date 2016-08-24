package com.tingyun.database.mysql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/6/29.
 */
public class TestPattern {

    public static final int PATTERN_SWITCHES = Pattern.DOTALL | Pattern.CASE_INSENSITIVE;

    private static final Pattern SELECT_PATTERN = Pattern.compile("^\\s*(select).*?\\sfrom[\\s\\[]+([^\\]\\s,)(;]*).*", PATTERN_SWITCHES);

    private static final Pattern NR_HINT_PATTERN = Pattern.compile("\\s*/\\*\\s*nrhint\\s*:\\s*([^\\*]*)\\s*\\*/\\s*([^\\s]*).*", Pattern.DOTALL);


    public static void  main(String[] args){
        Matcher matcher = SELECT_PATTERN.matcher("select * from a.user where 1 = 1 ;");
        if(matcher.matches()){
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }

         matcher = NR_HINT_PATTERN.matcher("select * from user where 1 = 1 ");
        if(matcher.matches()){
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }


        float aa = 81.326f;
        System.out.println((long)(aa*1000));


    }

}
