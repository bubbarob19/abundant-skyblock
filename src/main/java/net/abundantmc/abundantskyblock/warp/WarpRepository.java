package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.abundantmc.abundantskyblock.common.infrastructure.Mapper;
import net.abundantmc.abundantskyblock.common.infrastructure.MongoRepository;
import net.abundantmc.abundantskyblock.warp.entity.WarpEntity;
import org.bson.Document;

import java.util.Optional;

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

    public Optional<WarpEntity> findFromName(String name) {
        Document document = collection.find(Filters.eq("name", name)).first();
        if (document == null || document.isEmpty())
            return Optional.empty();
        else
            return Optional.of(mapper.mapFrom(document));
    }
}
