package com.bjethwan;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ToastInjectorVisitor  extends ClassVisitor implements Opcodes{

	public ToastInjectorVisitor(int arg0) {
		super(327680);
	}

	public ToastInjectorVisitor(ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) 
	{
		MethodVisitor mv;
		cv.visit(version, access, name, signature, superName, interfaces);
		System.out.println("name: " + name);
		System.out.println("superName: " + superName);
		String activityNameForToast = name.substring(name.lastIndexOf("/")+1);
		
		if(superName!=null && superName.contains("android/app/Activity")){
			System.out.println("TWEAKING");
			
			mv = cv.visitMethod(ACC_PROTECTED, "onStart", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "android/app/Activity", "onStart", "()V", false);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKEVIRTUAL, name, "getApplicationContext", "()Landroid/content/Context;", false);
			mv.visitLdcInsn("Hello "+ activityNameForToast + ".onStart()");
			mv.visitInsn(ICONST_0);
			mv.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
			mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(3, 1);
			mv.visitEnd();
		}
	}

}
