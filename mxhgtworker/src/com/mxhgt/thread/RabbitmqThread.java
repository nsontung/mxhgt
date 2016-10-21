package com.mxhgt.thread;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by tungns on 10/4/16.
 */
public class RabbitmqThread implements Runnable {

    private final static Logger logger = Logger.getLogger(RabbitmqThread.class);



    private final static String QUEUE_NAME = "notification";

    public RabbitmqThread() throws IOException {
        logger.info("Starting RabbitmqThread...");

    }

    @Override
    public void run()  {
        logger.info("Running RabbitmqThread...");


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            logger.debug(" [*] Waiting for messages. To exit press CTRL+C");


            Consumer consumer = new DefaultConsumer(channel) {

                private  int  receivedCount = 0;

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.debug(" [x] Received " + receivedCount++ + ": '" + message + "'");

                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }






    }
}
