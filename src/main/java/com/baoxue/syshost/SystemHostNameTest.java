package com.baoxue.syshost;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/8/10.
 */
public class SystemHostNameTest {


    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("Agent Host: " + address.getHostName() + " IP: " + address.getHostAddress());
    }
}
