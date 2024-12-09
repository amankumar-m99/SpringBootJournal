package net.m99.journalApp.repository;

import net.m99.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUsername(String username);
}
