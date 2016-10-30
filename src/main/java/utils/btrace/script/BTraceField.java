package utils.btrace.script;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Self;

import java.util.HashMap;

import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.println;

/**
 * Created by baoxue on 16/10/29.
 */
@BTrace
public class BTraceField {
    @OnMethod(clazz="java.util.HashMap", method="put")
    public static void m(@Self HashMap map, Object Key, Object value) {
        // all calls to the methods with signature "()"
        println("====================");
        print(Key);
        print(":");
        println(value);
    }

    @OnMethod(clazz="HelloBTrace", method="test")
    public static void d(@Self Object obj, int age){
        print("test method is called, input age=");
        println(age);
    }

}
