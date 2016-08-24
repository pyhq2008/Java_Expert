package com.baoxue.addtime;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2015/5/15.
 */
public class AddTimerMethodAdapter extends MethodAdapter {
    public AddTimerMethodAdapter(MethodVisitor mv) {
        super(mv);
    }
    @Override
    public void visitCode() {
        mv.visitCode();
        Label l0 = new Label();

        mv.visitLabel(l0);
        Label l1 = new Label();

        mv.visitLabel(l1);
        Label l4 = new Label();

        mv.visitLabel(l4);
        mv.visitLocalVariable("this", "Lcom/tingyun/MethodTest;", null, l0, l4, 0);
        mv.visitLocalVariable("startTime", "J", null, l1, l4, 1);
        System.out.println("label l0 :" + l0.getOffset());
        System.out.println("label l1 :" + l1.getOffset());
        System.out.println("label l4 :" + l4.getOffset());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System","nanoTime", "()J");
        mv.visitVarInsn(Opcodes.LSTORE, 1);
    }
    @Override
    public void visitInsn(int opcode) {
        Label l2 = new Label();

        mv.visitLabel(l2);
        Label l4 = new Label();

        mv.visitLabel(l4);
        mv.visitLocalVariable("endTime", "J", null, l2, l4, 3);
        System.out.println("label l2 :" + l2.getOffset());
        System.out.println("label l4 :" + l4.getOffset());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System","nanoTime", "()J");
        mv.visitVarInsn(Opcodes.LSTORE, 3);

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitLdcInsn("\u6d88\u8017\u65f6\u95f4\u4e3a\uff08\u7eb3\u79d2\uff09\uff1a");
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        mv.visitInsn(opcode);
    }
}
