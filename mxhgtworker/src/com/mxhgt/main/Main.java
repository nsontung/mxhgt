package com.mxhgt.main;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mxhgt.thread.RabbitmqThread;
import org.bson.Document;



/**
 * Created by tungns on 10/1/16.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Starting MXHGT worker...");

//        Thread t = new Thread(new RabbitmqThread());
//        t.start();

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("mxhgt");
        MongoCollection<org.bson.Document> c_posts = db.getCollection("c_user");

        Document obj = new Document();

        obj.put("name", "tungns");
        obj.put("age", "23");

        c_posts.insertOne(obj);

    }

}
