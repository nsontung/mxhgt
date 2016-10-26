package com.mxhgt.main;

import com.mxhgt.controller.App;
import com.mxhgt.thread.MongoThread;
import com.mxhgt.thread.RabbitmqThread;
import com.mxhgt.thread.RmqBenchmarkThread;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;



/**
 * Created by tungns on 10/1/16.
 */
public class Main {



    public static void main(String[] args) throws IOException {

        PropertyConfigurator.configure("./etc/log4j.properties");
        Logger logger = Logger.getLogger(Main.class);
        logger.debug("MXHGT is starting...");

//        Thread rbThread = new Thread(new RabbitmqThread());
//        rbThread.start();
//
//        Thread bmThread = new Thread(new RmqBenchmarkThread());
//        bmThread.start();


        App.instance();


    }

}
