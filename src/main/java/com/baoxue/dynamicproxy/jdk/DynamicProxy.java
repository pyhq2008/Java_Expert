package com.baoxue.dynamicproxy.jdk;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;
public class DynamicProxy
{
    public static void main( String args[] )
    {
        BookOrderImpl bookOrderImpl = new BookOrderImpl();
        BookOrder proxyBookOrder = (BookOrder)Proxy.newProxyInstance(BookOrder.class.getClassLoader(),
                new Class[]{BookOrder.class},
                new BookOrderProxy(bookOrderImpl));

        proxyBookOrder.order();

        try {
            //生成代理后的字节码文件
            doGenerateProxyClassFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doGenerateProxyClassFile() throws IOException {
        String name = "ProxyBookOrder";
        FileOutputStream out = null;
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { BookOrder.class } );
        try
        {
            out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }finally{
            if(out !=null){
                out.close();
            }
        }
    }
}