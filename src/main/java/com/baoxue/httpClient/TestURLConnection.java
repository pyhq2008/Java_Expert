package com.baoxue.httpClient;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Administrator on 2015/8/4.
 */
public class TestURLConnection {


    public static void main(String[] args) throws IOException {
        URL url = new URL("http://192.168.2.51:8080/newlens-test/saveforc3p0mysql");
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
//        connection.connect();

        System.out.println(url.getProtocol());

        System.out.println(connection.getContentType());
        System.out.println(connection.getContentEncoding());
        System.out.println(connection.getContentLength());
        System.out.println(connection.getDate());
        Scanner in = new Scanner(connection.getInputStream());
        while (in.hasNextLine())
            System.out.println(in.nextLine());


    }
}
