package com.tingyun.addtime;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Administrator on 2015/5/15.
 */
public class AddTimeClassAdapter extends ClassAdapter {

    public AddTimeClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                               String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        if (name.equals("hello")) {
            mv = new AddTimerMethodAdapter(mv);
        }
        return mv;
    }
}
