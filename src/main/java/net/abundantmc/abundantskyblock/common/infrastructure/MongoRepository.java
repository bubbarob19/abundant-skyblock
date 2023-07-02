package net.abundantmc.abundantskyblock.common.infrastructure;

import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class MongoRepository<T> implements Repository<T, String> {
    @Override
    public Optional<T> findFromID(String id) {
        Document document = getCollection().find(Filters.eq("_id", id)).first();
        if (document == null || document.isEmpty())
            return Optional.empty();
        else
            return Optional.of(fromDocument(document));
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(getCollection().find().spliterator(), false).map(this::fromDocument).collect(Collectors.toList());
    }

    @Override
    public Optional<T> delete(String id) {
        Document document = getCollection().findOneAndDelete(Filters.eq("_id", id));
        if (document == null || document.isEmpty())
            return Optional.empty();
        else
            return Optional.of(fromDocument(document));
    }

    @Override
    public void save(T t) {
        Document mapped = mapToDocument(t);
        getCollection().replaceOne(Filters.eq("_id", mapped.getString("_id")), mapped, new ReplaceOptions().upsert(true));
    }

    public T fromDocument(Document document) {
        return getMapper().mapFrom(document);
    }

    public Document mapToDocument(T t) {
        return getMapper().mapTo(t);
    }

    public abstract MongoCollection<Document> getCollection();

    public abstract Mapper<T, Document> getMapper();
}
