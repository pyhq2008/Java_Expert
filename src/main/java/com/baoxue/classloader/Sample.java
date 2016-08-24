package com.baoxue.classloader;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/6/10.
 */
public class Sample {

        private Sample instance;

        public void setSample(Object instance) {
            this.instance = (Sample) instance;
        }



    @Test
    public void testClassIdentity() {
        String classDataRootPath = "D:\\IdeaProjects\\asmTest\\out\\production\\asmTest\\com\\tingyun\\classloader";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.tingyun.classloader.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
