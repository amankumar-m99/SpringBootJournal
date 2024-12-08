package net.m99.journalApp.repository;

import net.m99.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntity, ObjectId> {
}
