package com.bjethwan;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Copy {
    public static void main(final String args[]) throws Exception {
        
    	FileInputStream  fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);

        ClassReader cr = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        
        cr.accept(cw, 0);

        byte[] byteCode = cw.toByteArray();
        
        fos.write(byteCode);
        fos.close();
    }
}


