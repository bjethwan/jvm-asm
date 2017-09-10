package com.bjethwan;

/**
 * Use Javap (The disasssembler)
 * 
 * Basic Info	: javap sample1/bin/com/bjethwan/HelloWorld.class
 * Compiled Code: javap -c sample1/bin/com/bjethwan/HelloWorld.class
 * Verbose		: javap -v sample1/bin/com/bjethwan/HelloWorld.class
 * 
 * The verbose option would show the constant pool and file version info.
 * 
 * @author Bipin Jethwani
 *
 */
public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello, Java");
	}
	
}

