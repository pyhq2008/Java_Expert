package com.baoxue.concurrent.java7.chapter7.ch03.task;

import java.util.concurrent.TimeUnit;


/**
 * Task to be executed in the MyThread threads
 *
 */
public class MyTask implements Runnable {

	/**
	 * Main method of the Thread. Sleeps the thread during two seconds
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("I am running ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
