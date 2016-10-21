package com.mxhgt.thread;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by tungns on 10/21/16.
 */
public class RmqBenchmarkThread implements Runnable {

    private final String QUEUE_NAME = "notification";

    private int MESSAGE_NUMBER = 1000;

    private Logger logger = Logger.getLogger(RmqBenchmarkThread.class);
    @Override
    public void run() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Channel channel = null;

        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 0 ; i < MESSAGE_NUMBER; i++) {

                String message = "Hello " + i ;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                logger.debug(" [x] Sent " + i + ": '" + message + "'");
            }

            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
