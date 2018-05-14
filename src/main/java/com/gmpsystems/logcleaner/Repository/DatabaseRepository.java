package com.gmpsystems.logcleaner.Repository;

import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import org.bson.Document;

import java.util.ArrayList;

public interface DatabaseRepository {
    ArrayList<Document> getUsers();
}
