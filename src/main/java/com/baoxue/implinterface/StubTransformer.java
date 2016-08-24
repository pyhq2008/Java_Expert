package com.baoxue.implinterface;


import com.baoxue.extendclass.MyClassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * Created by Administrator on 2015/5/15.
 */
public class StubTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader,
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
//        if ("com/tingyun/implinterface/InterfaceImplTest".equals(className)) {
            System.out.println("Load "+ className +" From Transformer");
            try {
                byte[] bytes = readByte(className.replace("/", "."));
                MyClassLoader classLoader = new MyClassLoader();
                Class clas = classLoader.defineClass(className.replace("/", "."), bytes);
                Method method = clas.getMethod("desc",null);
                method.invoke(clas.newInstance(),null);
                return bytes;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//        }

        return null;
    }

    public byte[] readByte(String fileName) throws FileNotFoundException {
        ClassWriter cwa = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        String str = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        PrintWriter printWriter = new PrintWriter(new File("D:\\"+str));
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cwa, printWriter);
        AddImplInterfaceClassAdpater addImplInterfaceClassAdpater = new AddImplInterfaceClassAdpater(traceClassVisitor);
        try {
            ClassReader cr = new ClassReader(fileName);
            cr.accept(addImplInterfaceClassAdpater,  ClassReader.SKIP_DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cwa.toByteArray();
    }

}
