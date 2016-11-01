package com.mxhgt.thread;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mxhgt.utils.Config;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by tungns on 10/6/16.
 */
public class MongoThread extends Thread {

    private Logger logger = Logger.getLogger(MongoThread.class);

    BlockingQueue<String> queue = new LinkedBlockingDeque<>();


    @Override
    public void run() {

        MongoClient mongoClient = new MongoClient(Config.MONGO_IP, Config.MONGO_PORT);
        MongoDatabase db = mongoClient.getDatabase("mxhgt");
        MongoCollection<Document> c_posts = db.getCollection("c_post");

        int counter = 0;
        while(true)
        {
            try {
                String s = queue.take();

                Document obj = new Document();

                obj.put("message", s);

                c_posts.insertOne(obj);
                logger.debug("Inserted post: " + counter++ );
            } catch (InterruptedException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }

    public void put(String input) {
        try {
            queue.put(input);
        } catch (InterruptedException e) {
            logger.warn(e.getMessage(), e);
        }
    }


}
