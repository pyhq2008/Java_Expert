package com.baoxue.concurrent.java7.chapter1.ch12;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
