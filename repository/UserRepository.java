package com.myFirstApplication.journalApp.repository;

import com.myFirstApplication.journalApp.entity.JournalEntry;
import com.myFirstApplication.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
