package com.tingyun.extendclass;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Administrator on 2015/5/20.
 */
public class AddExtendsClassAdapter extends ClassAdapter {


    public AddExtendsClassAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        cv.visit(version,access,name,signature,"com/tingyun/extendclass/Test",interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        if(name.equals("<init>")){
             mv = new AddExtendsMethodAdapter(mv);
            return mv;
        }
        return mv;
    }



}
