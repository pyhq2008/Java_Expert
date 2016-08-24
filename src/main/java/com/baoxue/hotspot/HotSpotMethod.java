package com.baoxue.hotspot;

import com.google.common.collect.Lists;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class HotSpotMethod implements JSONAware {

    private String classAndMethod;

    private long startTime;

    private long duration;

    private List<HotSpotMethod> childHotSpotMethodList = Lists.newLinkedList();

    public HotSpotMethod(){

    }

    public HotSpotMethod(String classAndMethod, long startTime, long duration, List<HotSpotMethod> childHotSpotMethodList){
        this.classAndMethod = classAndMethod;
        this.startTime = startTime;
        this.childHotSpotMethodList = childHotSpotMethodList;
        this.duration = duration;

    }
    public String getClassAndMethod() {
        return classAndMethod;
    }

    public void setClassAndMethod(String classAndMethod) {
        this.classAndMethod = classAndMethod;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public List<HotSpotMethod> getChildHotSpotMethodList() {
        return childHotSpotMethodList;
    }

    public void setChildHotSpotMethodList(List<HotSpotMethod> childHotSpotMethodList) {
        this.childHotSpotMethodList = childHotSpotMethodList;
    }

//    @Override
//    public void writeJSONString(Writer out) throws IOException {
//        JSONArray.writeJSONString(Arrays.asList(new Serializable[]{ //
//                this.classAndMethod, //
//                this.duration, //
//                DataSenderWriter.toJSONString(this.childHotSpotMethodList) //
//        }), out);
//    }
    public String toJSONString() {
        List<Object> result = new ArrayList<Object>(3);
        result.add(this.classAndMethod);
        result.add( this.duration);
        result.add(this.childHotSpotMethodList);


        return JSONArray.toJSONString(result);
    }

//    public HotSpotMethod checkIn(HotSpotMethod hotSpotMethod){
//
//        if(this.classAndMethod.equals(hotSpotMethod.getClassAndMethod()) && this.duration == 0L){
//            return this;
//        }
//
//        for(HotSpotMethod hot : this.getChildHotSpotMethodList()){
//             return  hot.checkIn(hotSpotMethod);
//        }
//
//        return null;
//    }

//    public HotSpotMethod addMethod(HotSpotMethod hotSpotMethod,boolean isChild){
//        if(isChild){
//            this.childHotSpotMethodList.add(hotSpotMethod);
//            return hotSpotMethod;
//        }
//        return  null;
//    }


}
