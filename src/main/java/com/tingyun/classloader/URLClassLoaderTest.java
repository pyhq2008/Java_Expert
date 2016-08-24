package com.tingyun.classloader;

import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author ashraf
 *
 */
public class URLClassLoaderTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		// Getting the jar URL which contains target class
		URL[] classLoaderUrls = new URL[]{new URL("file:///D:\\IdeaProjects\\asmTest\\out\\artifacts\\bean\\bean.jar")};
		
		// Create a new URLClassLoader 
		URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        System.out.println(urlClassLoader.getClass().getCanonicalName());
		
		// Load the target class
        Class<?> beanClass = urlClassLoader.loadClass("com.tingyun.classloader.Bean");
        
        // Create a new instance from the loaded class
        Constructor<?> constructor = beanClass.getConstructor();
        Object beanObj = constructor.newInstance();
        
        // Getting a method from the loaded class and invoke it
        Method method = beanClass.getMethod("sayHello");
        method.invoke(beanObj);
        System.out.println(Bean.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(Type.getDescriptor(Bean.class));

	}

}
