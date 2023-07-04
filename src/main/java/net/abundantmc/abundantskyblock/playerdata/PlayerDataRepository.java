package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.client.MongoCollection;
import net.abundantmc.abundantskyblock.common.infrastructure.Mapper;
import net.abundantmc.abundantskyblock.common.infrastructure.MongoRepository;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import org.bson.Document;

@Singleton
public class PlayerDataRepository extends MongoRepository<PlayerDataEntity> {
    private final MongoCollection<Document> collection;
    private final PlayerDataMapper mapper;

    @Inject
    public PlayerDataRepository(@Named("playerData") MongoCollection<Document> collection, PlayerDataMapper mapper) {
        this.collection = collection;
        this.mapper = mapper;
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    @Override
    public Mapper<PlayerDataEntity, Document> getMapper() {
        return mapper;
    }

    public int getDocumentCount() {
        return (int) collection.countDocuments();
    }
}
