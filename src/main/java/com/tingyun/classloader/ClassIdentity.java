package com.tingyun.classloader;

public class ClassIdentity {

	public static void main(String[] args) {
		new ClassIdentity().testClassIdentity();
	}
	
	public void testClassIdentity() {
		String classDataRootPath = "D:\\javaAgent\\tingyun-agent-java\\tingyun-agent-api\\";
		String classDataRootPath2 = "D:\\javaAgent\\tingyun-agent-java\\tingyun-agent-proxy\\";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath2);
		String className = "com.tingyun.api.agent.TingYun";
		try {
			Class<?> class1 = fscl1.loadClass(className);
			System.out.println(class1.getClassLoader());
			Object obj1 = class1.newInstance();
			System.out.println(class1.getClassLoader().getClass().getName());
			fscl2.loadClass(class1.getClassLoader().getClass().getName());
			Class<?> class2 = fscl2.loadClass(className);
			System.out.println(class2.getClassLoader());
			Object obj2 = class2.newInstance();
			obj1 = obj2;
//			Method setSampleMethod = class1.getMethod("setSample", Object.class);
//			setSampleMethod.invoke(obj1, obj2);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
