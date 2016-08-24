package com.baoxue.concurrent.java7.chapter1.ch08;

/**
 * unchecked Exception Ҳ�ܹ�ͨ��try catch ����,���һ�����ExceptionHandler
 * Runnable class than throws and Exception
 *
 */
public class Task implements Runnable {


	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		try{
			// The next instruction always throws and exception
			int numero=Integer.parseInt("TTT");
		}catch (Exception e){
			System.out.println("I catched the runtime");
			e.printStackTrace();
		}

		System.out.println("I catched the runtime   dsdfsd");
		try {
			Thread.sleep(10*1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
