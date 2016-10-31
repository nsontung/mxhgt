package com.mxhgt.controller;

import com.mxhgt.thread.MongoThread;
import com.mxhgt.thread.RabbitmqThread;
import com.mxhgt.thread.RmqBenchmarkThread;

/**
 * Created by tungns on 10/26/16.
 */
public class App {

    private static App ins = null;

    private MongoThread mongoThread = new MongoThread();

    private RabbitmqThread rabbitmqThread = new RabbitmqThread();

    private RmqBenchmarkThread rmqBenchmarkThread = new RmqBenchmarkThread();


    //make app singleton
    private App(){


    }

    public static App instance() {
        if(ins == null){
            ins = new App();
        }

        return ins;
    }

    public void start(){

        mongoThread.start();
        rabbitmqThread.start();
        rmqBenchmarkThread.start();

    }

    public void stop(){
        mongoThread.interrupt();
        rabbitmqThread.interrupt();
        rmqBenchmarkThread.interrupt();
    }

    public void addMongoMessage(String message){
        mongoThread.put(message);
    }


}
