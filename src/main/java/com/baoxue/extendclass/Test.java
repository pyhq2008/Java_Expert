package com.baoxue.extendclass;

import com.newrelic.agent.deps.org.objectweb.asm.Type;
import com.newrelic.api.agent.weaver.NewField;

/**
 * Created by Administrator on 2015/5/20.
 */
public class Test {

    public static final String NEW_FIELD_ANNOTATION_DESCRIPTOR = Type.getDescriptor(NewField.class);

    public void desc(){
        System.out.println("I am Test's desc method...executed by " + this.getClass().getName());
    }


    public static  void  main(String[] args){
        System.out.println(NEW_FIELD_ANNOTATION_DESCRIPTOR);
    }
}
