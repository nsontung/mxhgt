package com.mxhgt.thread;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * Created by tungns on 10/4/16.
 */
public class RabbitmqThread implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(RabbitmqThread.class.getName());

    private final static String QUEUE_NAME = "hello";

    public RabbitmqThread(){
        LOGGER.info("Starting RabbitmqThread...");
    }

    @Override
    public void run()  {
        LOGGER.info("Running RabbitmqThread...");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel = null;

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
