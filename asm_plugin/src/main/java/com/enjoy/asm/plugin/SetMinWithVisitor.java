package com.enjoy.asm.plugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SetMinWithVisitor extends ClassVisitor {

    private String className;

    public SetMinWithVisitor(ClassVisitor cv, String fileName) {
        super(Opcodes.ASM5, cv);
        className = fileName.substring(0, fileName.lastIndexOf("."));
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                                     String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature,
                exceptions);
        if (name.equals("dispatchCreate")) {
            System.out.println("name:" + name + " desc:" + desc + " signature:" + signature + " access:" + access);
            return new DispatchCreateVisitor(mv, access, name, desc, className);
        }

        return mv;
    }

}