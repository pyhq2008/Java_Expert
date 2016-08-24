package com.baoxue.collection;

import com.newrelic.agent.deps.org.json.simple.parser.JSONParser;
import com.newrelic.agent.deps.org.json.simple.parser.ParseException;

import java.util.Map;

/**
 * Created by Administrator on 2015/12/11.
 */
public class TEst {

    public static void  main(String[] args) throws ParseException {
        String s = "{\"id\":\"kJQhRvj26cg#EImb-HaEEHo\",\"time\":{\"ex\":0,\"db\":0,\"rds\":0,\"duration\":1904,\"mc\":0,\"mon\":0,\"qu\":0,\"code\":1904},\"action\":\"WebAction\\/Servlet\\/cxf\",\"trId\":\"c553d7282b001e88\"}";
        JSONParser jsonParser = new JSONParser();
        Map<String, Object> arr = (Map<String, Object>) jsonParser.parse(s);
    }
}
