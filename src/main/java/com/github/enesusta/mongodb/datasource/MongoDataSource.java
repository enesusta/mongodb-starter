package com.github.enesusta.mongodb.datasource;

import com.github.enesusta.mongodb.configuration.MongoURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public interface MongoDataSource<T> {
    MongoCollection<Document> getDocument(String documentName);
    MongoDatabase getDatabase();
    MongoClient getClient();
}
