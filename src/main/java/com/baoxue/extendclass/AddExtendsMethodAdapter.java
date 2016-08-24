package com.baoxue.extendclass;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Administrator on 2015/5/20.
 */
public class AddExtendsMethodAdapter extends MethodAdapter {

    public AddExtendsMethodAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc){
        mv.visitMethodInsn(opcode, "com/baoxue/extendclass/Test",name,desc);
    }


}
