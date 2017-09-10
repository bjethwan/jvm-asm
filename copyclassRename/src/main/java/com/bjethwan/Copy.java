package com.bjethwan;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Copy {
    public static void main(final String args[]) throws Exception {
        
    	FileInputStream  fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        
        String fileNameWithoutExtenstion = args[1].substring(0, 
        		args[1].lastIndexOf("."));

        ClassReader cr = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassRenameVisitor crv = new ClassRenameVisitor(fileNameWithoutExtenstion, cw);
        
        cr.accept(crv, 0);

        byte[] byteCode = cw.toByteArray();
        
        fos.write(byteCode);
        fos.close();
    }
}


