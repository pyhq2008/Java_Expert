package com.tingyun.classloader.test;

import java.io.*;

/**
 * Created by Administrator on 2015/11/24.
 */
public class MyClassLoader extends ClassLoader {

    private String name;    //加载器名称
    private String path = "E:\\WorkSpace\\ClassLoaderTest\\";  //加载路径
    private static final String HOME = "E:\\WorkSpace\\ClassLoaderTest\\";
    private final String classFileType = ".class";

    public MyClassLoader(String name) {
        super();
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        if(data == null)
            throw new ClassNotFoundException();
        return this.defineClass(name, data, 0, data.length);

    }

    private byte[] loadClassData(String name) {

        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
//        System.out.println("  classloader:" + this.name + " try to load");
        try {
            //类名转化为路径
            name = name.replace(".", "\\");
            is = new FileInputStream(new File(path + name + classFileType));

            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {

                baos.write(ch);
            }

            data = baos.toByteArray();
        }
        catch (FileNotFoundException e) {
//            e.printStackTrace();
            return null;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                is.close();
                baos.close();
            }
            catch (Exception e2) {
            }
        }
        return data;
    }


    public static void main(String[] args) throws Exception {
        //假定的系统加载器
        MyClassLoader father = new MyClassLoader("father");
        father.setPath(HOME + "syslib\\");

        MyClassLoader child = new MyClassLoader(father, "child");
        child.setPath(HOME + "ext\\");

        MyClassLoader user = new MyClassLoader("user");
        user.setPath(HOME + "usr\\");
        System.out.println("-------------test parent--------------");
        //测试父加载器关系
        traverseParent(child);
        System.out.println("-------------test load begin from child--------------");
        //测试加载
        test(child);
        //测试命名空间
        System.out.println("-------------test namespace--------------");
        testNameSpace(user);

    }

    public static void traverseParent(ClassLoader loader) throws Exception{
        if(loader == null) return;
        System.out.println("travase classloader:" + loader.toString());
        while(loader.getParent() != null){
            System.out.println(loader.getParent());
            loader = loader.getParent();
        }
    }

    public static void test(ClassLoader loader) throws Exception {
        Class<?> clazz = loader.loadClass("com.tingyun.classloader.test.LoadedClass");
        Object object = clazz.newInstance();
    }

    public static void testNameSpace(ClassLoader loader) throws Exception {
        Class<?> clazz = loader.loadClass("com.tingyun.classloader.test.LoadedClass");
        Object object = clazz.newInstance();
        try{
            LoadedClass lc = (LoadedClass) object;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}