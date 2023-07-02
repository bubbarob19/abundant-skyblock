package net.abundantmc.abundantskyblock.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseModule extends AbstractModule {
    @Provides @Singleton
    private static MongoDatabase provideMongoDatabase(@Named("connectionString") String connectionStringValue,
                                                      @Named("database") String databaseValue) {
        ConnectionString connectionString = new ConnectionString(connectionStringValue);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(databaseValue);
    }

    @Provides @Singleton @Named("warp")
    private static MongoCollection<Document> provideWarpCollection(MongoDatabase database) {
        return database.getCollection("warp");
    }

    @Provides @Singleton @Named("playerData")
    private static MongoCollection<Document> providePlayerDataCollection(MongoDatabase database) {
        return database.getCollection("playerData");
    }
}
