package com.mxhgt.thread;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by tungns on 10/6/16.
 */
public class MongoThread implements Runnable {


    @Override
    public void run() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("mxhgt");
        MongoCollection<Document> c_posts = db.getCollection("c_user");

        Document obj = new Document();

        obj.put("name", "tungns");
        obj.put("age", "23");

        c_posts.insertOne(obj);
    }



}
