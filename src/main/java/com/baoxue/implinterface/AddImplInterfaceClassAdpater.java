package com.baoxue.implinterface;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2015/5/20.
 */
public class AddImplInterfaceClassAdpater extends ClassAdapter{


    public AddImplInterfaceClassAdpater(ClassVisitor classVisitor) {
        super(classVisitor);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        cv.visit(version, access, name, signature, superName, new String[]{"com/baoxue/implinterface/CommonInterface"});
    }

    @Override
    public void visitEnd(){
        MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC,"desc","()V",null,null);
        mv.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("I am from interface .......");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2,1);
        mv.visitEnd();
        cv.visitEnd();
    }




}
