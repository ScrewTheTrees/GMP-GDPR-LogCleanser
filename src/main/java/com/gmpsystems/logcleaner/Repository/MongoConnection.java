package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoConnection {

    private static MongoConnection instance;
    public static void CreateMongoConnection(LogCleanerConfig logCleanerConfig) {
        instance = new MongoConnection(logCleanerConfig);
    }


    private com.mongodb.client.MongoClient mongoClient;
    private LogCleanerConfig logCleanerConfig;

    private MongoConnection(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
        MongoClients.create("mongodb://" + logCleanerConfig.getDatabaseHostname() + ":" + logCleanerConfig.getDatabasePort());
    }


    public MongoCollection<Document> getUsers() {
        return mongoClient.getDatabase(logCleanerConfig.getDatabaseName()).getCollection(logCleanerConfig.getDatabaseCollection());
    }

    public static MongoConnection getInstance() {
        return instance;
    }
}
