package com.github.enesusta.mongodb;

import com.github.enesusta.mongodb.configuration.MongoURI;
import com.github.enesusta.mongodb.datasource.DefaultMongoDataSource;
import com.github.enesusta.mongodb.datasource.MongoDataSource;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App {

    public static void main(String[] args) {

        final MongoURI mongoSettings = new MongoURI.MongoURIBuilder()
                .user("root")
                .password("pass")
                .host("localhost")
                .database("test")
                .build();

        MongoDataSource<Document> mongoDataSource = DefaultMongoDataSource.getInstance(mongoSettings);
        MongoCollection<Document> mongoCollection = mongoDataSource.getDocument("test");

        Document doc = new Document("name", "enes")
                .append("soyisim", "usta");

        mongoCollection.insertOne(doc);

    }
}
