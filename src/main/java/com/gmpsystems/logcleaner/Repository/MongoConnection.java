package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import com.mongodb.Block;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class MongoConnection implements DatabaseRepository {

    private static MongoConnection instance;
    public static void CreateConnection(LogCleanerConfig logCleanerConfig) {
        instance = new MongoConnection(logCleanerConfig);
    }


    private com.mongodb.client.MongoClient mongoClient;
    private LogCleanerConfig logCleanerConfig;

    private MongoConnection(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
        mongoClient = MongoClients.create("mongodb://" + logCleanerConfig.getDatabaseHostname() + ":" + logCleanerConfig.getDatabasePort());
    }


    public ArrayList<Document> getUsers() {
        ArrayList<Document> documents = new ArrayList<>();
        MongoCollection<Document> db = mongoClient.getDatabase(logCleanerConfig.getDatabaseName()).getCollection(logCleanerConfig.getDatabaseCollection());
        db.find().forEach((Block<? super Document>) documents::add);

        return documents;
    }

    public static MongoConnection getInstance() {
        return instance;
    }
}
