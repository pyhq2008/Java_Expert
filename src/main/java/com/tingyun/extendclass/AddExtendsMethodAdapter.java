package com.tingyun.extendclass;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2015/5/20.
 */
public class AddExtendsMethodAdapter extends MethodAdapter {

    public AddExtendsMethodAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc){
        mv.visitMethodInsn(opcode,"com/tingyun/extendclass/Test",name,desc);
    }


}
