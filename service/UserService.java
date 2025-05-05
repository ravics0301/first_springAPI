package com.myFirstApplication.journalApp.service;

import com.myFirstApplication.journalApp.entity.JournalEntry;
import com.myFirstApplication.journalApp.entity.User;
import com.myFirstApplication.journalApp.repository.JournalEntryRepository;
import com.myFirstApplication.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(ObjectId id) {
        return  userRepository.findById(id);
    }
    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }
    // controller --> service --> repository
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
