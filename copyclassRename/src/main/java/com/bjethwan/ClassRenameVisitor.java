package com.bjethwan;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassRenameVisitor extends ClassVisitor{
	private String oriClassName;
	private String newClassName;
	
	public ClassRenameVisitor(String newName) {
		super(327680);
		this.newClassName = newName;
	}
	
	public ClassRenameVisitor(String newName, ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.newClassName = newName;
	}
	
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) 
	{
			oriClassName = name;
			cv.visit(version, access, newClassName, signature, superName, interfaces);	
	}
	
	
	
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) 
	{
			if(name==oriClassName){
				return cv.visitMethod(access, newClassName, desc, signature, exceptions);
			}
			return cv.visitMethod(access, name, desc, signature, exceptions);
	}
}

