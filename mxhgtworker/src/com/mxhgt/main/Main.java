package com.mxhgt.main;

import com.mxhgt.thread.RabbitmqThread;


/**
 * Created by tungns on 10/1/16.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting MXHGT worker...");

//        Thread t = new Thread(new RabbitmqThread());
//        t.start();

        Runnable r =  () -> {
            System.out.println("Running nhe");
        };

        Thread t = new Thread(() -> {
        });
        t.run();
        Thread.sleep(2000);
    }

}
