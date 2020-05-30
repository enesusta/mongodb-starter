package com.github.enesusta.mongodb.datasource;

import com.github.enesusta.mongodb.configuration.MongoURI;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class DefaultMongoDataSource implements MongoDataSource<Document> {

    private final static Map<String, MongoCollection<Document>> flyweightMongoCollectionMap = new HashMap<>(5);
    private final static DefaultMongoDataSource instance = null;
    private final MongoURI mongoURI;

    private DefaultMongoDataSource(final MongoURI mongoURI) {
        this.mongoURI = mongoURI;
    }

    public static DefaultMongoDataSource getInstance(final MongoURI mongoURI) {
        if (instance == null) return new DefaultMongoDataSource(mongoURI);
        return instance;
    }

    @Override
    public final MongoCollection<Document> getDocument(final String collectionName) {

        MongoCollection<Document> mongoCollection = flyweightMongoCollectionMap.get(collectionName);

        if (mongoCollection == null) {
            mongoCollection = getDatabase().getCollection(collectionName);
            flyweightMongoCollectionMap.put(collectionName, mongoCollection);
        }

        return mongoCollection;
    }

    @Override
    public final MongoDatabase getDatabase() {
        return getClient().getDatabase(mongoURI.getDb());
    }

    @Override
    public final MongoClient getClient() {

        final MongoCredential credential =
                MongoCredential.createCredential(mongoURI.getUsername(), mongoURI.getDb(), mongoURI.getPwd().toCharArray());

        final MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(builder -> {
                    builder.hosts(Arrays.asList(new ServerAddress(mongoURI.getHost(), mongoURI.getPort())));
                })
                .build();

        return MongoClients.create(settings);
    }


}
