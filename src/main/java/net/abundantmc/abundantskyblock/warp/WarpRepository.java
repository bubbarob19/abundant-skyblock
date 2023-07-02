package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.client.MongoCollection;
import net.abundantmc.abundantskyblock.common.infrastructure.Mapper;
import net.abundantmc.abundantskyblock.common.infrastructure.MongoRepository;
import org.bson.Document;

@Singleton
public class WarpRepository extends MongoRepository<WarpEntity> {
    private final MongoCollection<Document> collection;
    private final WarpMapper mapper;

    @Inject
    public WarpRepository(@Named("warp") MongoCollection<Document> collection, WarpMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    @Override
    public Mapper<WarpEntity, Document> getMapper() {
        return mapper;
    }
}
