package com.mxhgt.thread;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by tungns on 10/4/16.
 */
public class RabbitmqThread extends Thread {

    private final Logger logger = Logger.getLogger(RabbitmqThread.class);



    private final static String QUEUE_NAME = "notification";

    public RabbitmqThread() {
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

            long startTime = System.currentTimeMillis();

            Consumer consumer = new DefaultConsumer(channel) {

                private  int  receivedCount = 0;

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.debug(" [x] Received " + receivedCount++ + ": '" + message + "'");




                    if(receivedCount == RmqBenchmarkThread.MESSAGE_NUMBER) {

                        logger.info("Total time: " + (System.currentTimeMillis() - startTime) + "ms");
                    }
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
