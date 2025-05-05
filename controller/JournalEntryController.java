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
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName)
    {
       User user =  userService.findByUserName(userName);
         List<JournalEntry> all = user.getJournalEntries();
         if(all != null && !all.isEmpty()) {
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId journalId) {
       Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(journalId);

        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{journalId}")
    public boolean deleteEntryById(@PathVariable ObjectId journalId) {

 journalEntryService.deleteById(journalId);
 return true;

    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntries(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

//    @PutMapping("id/{id}")
//    public JournalEntry updateJournal(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
//
//        JournalEntry old =  journalEntryService.getJournalEntryById(id).orElse(null);
//        if(old != null) {
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
//            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());
//        }
//        journalEntryService.saveEntry(old);
//        return old;
//    }
}

