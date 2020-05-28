package com.github.enesusta.mongodb.datasource;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DefaultMongoDataSource implements MongoDataSource<Document> {

    @Override
    public MongoCollection<Document> getDocument(String documentName) {
        return null;
    }

    @Override
    public MongoDatabase getDatabase() {
        MongoClient mongoClient = new MongoClient();
        return null;
    }
}
