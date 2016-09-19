package com.io.blockio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步阻塞的Server
 * Created by baoxue on 16/9/12.
 */
public class MultiThreadEchoServer {

    private static ExecutorService tp = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable{
        Socket clientSocket;
        public HandleMsg(Socket clientSocket){
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try{
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                //从InputStream中读取客户端所发送的数据
                String inputline = null;
                long b = System.currentTimeMillis();
                while((inputline = is.readLine()) != null){
                    os.println(inputline);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend"+ (e-b) +"ms");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(is != null)
                        is.close();
                    if(os != null)
                        os.close();
                    clientSocket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        public static  void  main(String[] args){
            ServerSocket serverSocket = null;
            Socket clientSocket = null;

            try{
                serverSocket = new ServerSocket(8000);
            }catch (Exception e){
                e.printStackTrace();
            }

            while (true){

                try{
                    clientSocket = serverSocket.accept();
                    System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
                    tp.execute(new HandleMsg(clientSocket));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }
}
