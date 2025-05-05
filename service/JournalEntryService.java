package com.myFirstApplication.journalApp.service;

import com.myFirstApplication.journalApp.entity.JournalEntry;
import com.myFirstApplication.journalApp.entity.User;
import com.myFirstApplication.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveEntry(user);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return  journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id) {
          journalEntryRepository.deleteById(id);
    }
    // controller --> service --> repository
}
