package com.bjethwan;

import org.objectweb.asm.ClassReader;

public class Print {
    public static void main(final String args[]) throws Exception {
 
        ClassReader cr = new ClassReader("java.lang.Thread");
        ClassPrinter cp = new ClassPrinter();
        cr.accept(cp, 0);
    }
}


