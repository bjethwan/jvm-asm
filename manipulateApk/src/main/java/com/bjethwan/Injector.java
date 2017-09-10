package com.bjethwan;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Injector {

	public byte[] inject(byte[] inputClassBytes) {
		byte[] outputClassBytes = null;
		
		ClassReader cr = new ClassReader(inputClassBytes);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ToastInjectorVisitor tiv = new ToastInjectorVisitor(cw);
		cr.accept(tiv, 0);
		outputClassBytes = cw.toByteArray();
		return outputClassBytes;
	}

}
