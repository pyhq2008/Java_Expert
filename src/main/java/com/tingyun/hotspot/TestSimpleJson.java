package com.tingyun.hotspot;

import com.google.common.collect.Lists;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TestSimpleJson {


    public static void main(String[] args) throws IOException {
        List<HotSpotData> ll = Lists.newArrayList();
        HotSpotData hotSpotData = new HotSpotData();
        hotSpotData.setActionMetric("testTingyunActionName");
        hotSpotData.setActionDuration(1000L);
        HotSpotMethod rootMethod = new HotSpotMethod();
        rootMethod.setClassAndMethod("TingyunHotSpot#tinguyn");
        rootMethod.setDuration(10L);
        hotSpotData.setHotSpotMethod(rootMethod);


        HotSpotMethod secondMethod = new HotSpotMethod();
        secondMethod.setClassAndMethod("Service#Method");
        secondMethod.setDuration(55L);
        List<HotSpotMethod> list = Lists.newArrayList();
        list.add(secondMethod);
        rootMethod.setChildHotSpotMethodList(list);
        Map<String, Object> params = new HashMap<String, Object>();
        ll.add(hotSpotData);
        params.put("hotspots", ll);

        System.out.println(toJSONString(params));


    }

    public static final String toJSONString(Object obj) {
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        Writer writer;
        try {
            writer = new OutputStreamWriter(oStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            writer = new OutputStreamWriter(oStream);
        }
        try {
            JSONValue.writeJSONString(obj, writer);
            writer.close();
            return oStream.toString("UTF-8");
        } catch (IOException e) {
        }
        return JSONValue.toJSONString(obj);
    }


}
