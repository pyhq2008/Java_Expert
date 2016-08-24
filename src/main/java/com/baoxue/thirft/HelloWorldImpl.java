package com.baoxue.thirft;

public class HelloWorldImpl implements HelloWorldService.Iface {
	 
    public HelloWorldImpl() {
    }
 
    public String sayHello(String username){
        return "Hi," + username + " ,Welcome to the thrift's world !";
    }
 
}
