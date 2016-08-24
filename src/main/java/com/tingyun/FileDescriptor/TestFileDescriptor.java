package com.tingyun.FileDescriptor;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/7/16.
 */
public class TestFileDescriptor {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        FileDescriptor fd = null;
        boolean bool = false;

        try{
            // create new file output stream
            fos=new FileOutputStream("C://test.txt");

            // get file descriptor instance
            fd = fos.getFD();

            // test if the file is valid
            bool = fd.valid();

            // print
            System.out.print("Is file valid? "+bool);

        }catch(Exception ex){
            // if an error occurs
            ex.printStackTrace();
        }finally{
            if(fos!=null)
                fos.close();
        }
    }
}
