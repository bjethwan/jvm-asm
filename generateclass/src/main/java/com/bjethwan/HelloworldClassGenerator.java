package com.bjethwan;

import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class HelloworldClassGenerator implements Opcodes {

    public static void main(final String args[]) throws Exception {
        // Generates the bytecode corresponding to the following Java class:
        //
        // public class <<Input Class Name>> {
        // 		public static void main (String[] args) {
        // 			System.out.println("Hello world!");
        // 		}
        // }

    	String fullClassNameWithExtension = null;
    	if(args != null && args.length>0)
    		fullClassNameWithExtension = args[0];
    	else
    		fullClassNameWithExtension = "com/bjethwan/HelloASM.class";
    	
    	String fullClassName = fullClassNameWithExtension.substring(0, 
    			fullClassNameWithExtension.indexOf("."));
    	
    	
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_1, ACC_PUBLIC, fullClassName, null, "java/lang/Object", null);
        
        //MethodWriter for implicit constructor
        MethodVisitor c = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        c.visitVarInsn(ALOAD, 0);
        c.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        c.visitInsn(RETURN);
        c.visitMaxs(1, 1);
        c.visitEnd();

        //MethodWriter for the 'main' method
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mw.visitInsn(RETURN);
        mw.visitMaxs(2, 2);
        mw.visitEnd();

        byte[] code = cw.toByteArray();

        FileOutputStream fos = new FileOutputStream(fullClassNameWithExtension);
        fos.write(code);
        fos.close();

    }
}
