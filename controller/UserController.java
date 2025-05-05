package com.myFirstApplication.journalApp.controller;

import com.myFirstApplication.journalApp.entity.JournalEntry;
import com.myFirstApplication.journalApp.entity.User;
import com.myFirstApplication.journalApp.service.JournalEntryService;
import com.myFirstApplication.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userDb = userService.findByUserName(user.getUserName());

        if(userDb != null) {
            userDb.setUserName(userDb.getUserName());
            userDb.setPassword(userDb.getPassword());
            userService.saveEntry(userDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

