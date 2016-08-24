package com.baoxue.publicTest;

/**
 * Created by Administrator on 2015/11/5.
 */
public class App {

    public static void  main(String[] args){
        App app = new App();
        app.fixOperations(null);
    }


    private String fixOperations(String[] operations) {
        StringBuilder builder = new StringBuilder();
        for (String operation : operations) {
            if (operation.startsWith("/"))
                builder.append(operation);
            else {
                builder.append('/').append(operation);
            }
        }
        return builder.toString();
    }
}
