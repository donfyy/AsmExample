package com.enjoy.asm.plugin;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class DispatchCreateVisitor extends AdviceAdapter {

    private String className;
    private String methodName;
    private boolean inject;
    private int index;
    private int start, end;
    private Label mLabel;

    protected DispatchCreateVisitor(MethodVisitor mv, int access, String name, String desc,
                                    String className) {
        super(Opcodes.ASM5, mv, access, name, desc);
        methodName = name;
        this.className = className;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);

        if (IFNULL == opcode) {
            mLabel = newLabel();
            goTo(mLabel);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);

        System.out.println(
                "owner:" + owner + " name:" + name
        );

        if (name.contains("onCreate")) {
            mark(mLabel);
        }
    }

    @Override
    protected void onMethodEnter() {

    }

    @Override
    protected void onMethodExit(int opcode) {
    }
}