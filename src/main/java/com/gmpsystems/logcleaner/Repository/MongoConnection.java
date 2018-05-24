package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerDatabaseUnit;
import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import com.mongodb.Block;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class MongoConnection implements IDatabaseRepository {
    private com.mongodb.client.MongoClient mongoClient;
    private LogCleanerConfig logCleanerConfig;

    public MongoConnection(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
        mongoClient = MongoClients.create("mongodb://" + logCleanerConfig.getDatabaseHostname() + ":" + logCleanerConfig.getDatabasePort());
    }


    public ArrayList<CleanerDatabaseUnit> getUsers(CleanerCleanseInformation cleanerCleanseInformation) {
        ArrayList<Document> documents = new ArrayList<>();
        ArrayList<CleanerDatabaseUnit> units = new ArrayList<>();

        MongoCollection<Document> db = mongoClient.getDatabase(logCleanerConfig.getDatabaseName()).getCollection(logCleanerConfig.getDatabaseCollection());
        db.find().forEach((Block<? super Document>) documents::add);

        for (Document d : documents) {
            units.add(new CleanerDatabaseUnit(d.get(cleanerCleanseInformation.getReplaceFromField()).toString(), d.get(cleanerCleanseInformation.getReplaceToField()).toString()));
        }

        return units;
    }
}
