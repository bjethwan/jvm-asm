package com.bjethwan;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassRename extends ClassVisitor{

	private String oriClassName;
	
	public ClassRename() {
		super(327680);
	}
	
	public ClassRename(ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
	}
	
	public void visit(
				int version, 
				int access, 
				String name, 
				String signature, 
				String superName, 
				String[] interfaces) 
	{
			oriClassName = name;
			System.out.println(name + " extends " + superName + "implements " + Arrays.toString(interfaces));
			System.out.println("We are going to change the class name here to com/bjethwan/Bipin ");
			cv.visit(version, access, "com/bjethwan/Bipin", signature, superName, interfaces);
	}
	
	
	
	public MethodVisitor visitMethod(
				int access, 
				String name,
				String desc, 
				String signature, 
				String[] exceptions) 
	{
			System.out.println(" " + name + desc);
			if(name==oriClassName){
				return cv.visitMethod(access, "com/bjethwan/Bipin", desc, signature, exceptions);
			}
			return cv.visitMethod(access, name, desc, signature, exceptions);
	}
}
