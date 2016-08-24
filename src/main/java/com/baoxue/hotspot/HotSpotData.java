package com.baoxue.hotspot;

import com.google.common.collect.Maps;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/3.
 */
public class HotSpotData implements JSONStreamAware,JSONAware {

    private String actionMetric;

    private long actionDuration;

    private HotSpotMethod hotSpotMethod;

    public String getActionMetric() {
        return actionMetric;
    }

    public void setActionMetric(String actionMetric) {
        this.actionMetric = actionMetric;
    }

    public long getActionDuration() {
        return actionDuration;
    }

    public void setActionDuration(long actionDuration) {
        this.actionDuration = actionDuration;
    }

    public HotSpotMethod getHotSpotMethod() {
        return hotSpotMethod;
    }

    public void setHotSpotMethod(HotSpotMethod hotSpotMethod) {
        this.hotSpotMethod = hotSpotMethod;
    }


    public void writeJSONString(Writer out) throws IOException {
        List<Object> result = new ArrayList<Object>(3);
        Map<Object,Object> map = Maps.newHashMap();
        map.put("name",this.actionMetric);
        result.add(map);
        result.add(this.actionDuration);
        result.add(this.hotSpotMethod);
        JSONArray.writeJSONString(result, out);
//        JSONArray.writeJSONString(Arrays.asList(new Serializable[]{
//                this.actionMetric,
//                this.actionDuration,
//                JSONValue.toJSONString(this.hotSpotMethod)
//        }), out);

    }


    public String toJSONString() {
        return JSONArray.toJSONString(Arrays.asList(new Serializable[]{
                this.actionMetric,
                this.actionDuration,
                JSONValue.toJSONString(this.hotSpotMethod)
        }));
    }
}
