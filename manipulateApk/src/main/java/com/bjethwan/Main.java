package com.bjethwan;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		CodeInjectJar(args[0], args[1]);
		//CodeInjectJar("C:/asm-tests/nextActivity.jar", "C:/asm-tests/nextActivityTweaked.jar");
	}
	
	public static void CodeInjectJar(String in, String out) throws FileNotFoundException, IOException {
		
		JarInputStream jis = new JarInputStream(new FileInputStream(in));
		
		JarOutputStream jos = null;
		if (null == jis.getManifest()) {
			jos = new JarOutputStream(new FileOutputStream(out));
		} else {
			jos = new JarOutputStream(new FileOutputStream(out), jis.getManifest());
		}
		JarEntry entry;
		JarEntry newEntry;
		Injector inject = null;

		while ((entry = jis.getNextJarEntry()) != null) {
			try {
				if (entry.isDirectory()) {
					jos.putNextEntry(entry);
				} else {
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					byte buffer[] = new byte[2048];
					byte[] newContent = null;
					;
					while (true) {
						int read = jis.read(buffer);
						if (read < 0) {
							break;
						}
						os.write(buffer, 0, read);
					}

					if (entry.getName().contains(".class") && !entry.getName().startsWith("android/support")&& !entry.getName().startsWith("net/sqlcipher")) {
						inject = new Injector();
						newContent = inject.inject(os.toByteArray());
					}else{
						newContent = os.toByteArray();
					}
					os.close();
					newEntry = new JarEntry(entry.getName());
					newEntry.setSize(newContent.length);
					jos.putNextEntry(newEntry);
					jos.write(newContent);
				}

				jos.closeEntry();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		jis.close();
		jos.close();
		
	}

}
