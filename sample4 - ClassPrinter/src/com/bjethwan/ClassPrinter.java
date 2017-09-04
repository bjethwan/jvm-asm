package com.bjethwan;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter extends ClassVisitor{

	public ClassPrinter() {
		super(327680);
	}
	
	public void visit(
				int version, 
				int access, 
				String name, 
				String signature, 
				String superName, 
				String[] interfaces) 
	{
			System.out.println(name + " extends " + superName + "implements " + Arrays.toString(interfaces));
	}
	
	public void visitSource(
				String source, 
				String debug) 
	{
		System.out.println("source: " + source);
		System.out.println("debug: " + debug);
	}
	
	public FieldVisitor visitField(
				int access, 
				String name, 
				String desc,
				String signature, 
				Object value) 
	{
			System.out.println(" " + desc + " " + name);
			return null;
	}
	
	public MethodVisitor visitMethod(
				int access, 
				String name,
				String desc, 
				String signature, 
				String[] exceptions) 
	{
			System.out.println(" " + name + desc);
			return null;
	}


}
