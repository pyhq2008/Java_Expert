package com.baoxue.addtime;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by Administrator on 2015/5/15.
 */
public class StubTransformer implements ClassFileTransformer {

    private int i;

    public byte[] transform(ClassLoader loader,
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
//        System.out.println(className);
////        this.i++;
////        System.out.println(this.i);
//        System.out.println(loader.getClass().getName());


        if ("com/baoxue/addtime/MethodTest".equals(className)) {
            System.out.println("Load MethodTest From Transformer");
            try {
                return readByte(className.replace("/", "."));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public byte[] readByte(String fileName) throws FileNotFoundException {
        System.out.println("File name: " +  fileName);
        ClassWriter cwa = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        PrintWriter printWriter = new PrintWriter(new File("D:\\aa"));
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cwa, printWriter);
        AddTimeClassAdapter ca = new AddTimeClassAdapter(traceClassVisitor);
        try {
            ClassReader cr = new ClassReader(fileName);
            cr.accept(ca,  ClassReader.SKIP_DEBUG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cwa.toByteArray();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    //    public static void main(String[] args) throws FileNotFoundException {
//        StubTransformer stubTransformer = new StubTransformer();
//        stubTransformer.readByte("com.tingyun.addtime.MethodTest");
//    }
}
