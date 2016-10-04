package com.mxhgt.main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mxhgt.thread.RabbitmqThread;
import org.bson.Document;


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;


/**
 * Created by tungns on 10/1/16.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Starting MXHGT worker...");

        Thread t = new Thread(new RabbitmqThread());
        t.start();

//        MongoClient mongoClient = new MongoClient();
//        MongoDatabase db = mongoClient.getDatabase("mxhgt");
//        MongoCollection<org.bson.Document> c_posts = db.getCollection("c_post");

    }

}
