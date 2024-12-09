package net.m99.journalApp.controller;

import net.m99.journalApp.entity.JournalEntity;
import net.m99.journalApp.service.JournalEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalEntityService journalEntityService;

    @PostMapping
    public ResponseEntity<JournalEntity> addJournal(@RequestBody JournalEntity journalEntity){
        try{
            return new ResponseEntity<>(journalEntityService.save(journalEntity), HttpStatus.CREATED);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JournalEntity>> getAllJournals() {
        return new ResponseEntity<>(journalEntityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> getJournal(@PathVariable ObjectId id){
        JournalEntity journalEntity = journalEntityService.getById(id);
        HttpStatus status = (journalEntity != null)? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(journalEntity, status);
    }

    @PutMapping
    public ResponseEntity<JournalEntity> updateJournal(@RequestBody JournalEntity newJournalEntity){
        JournalEntity journalEntity = journalEntityService.update(newJournalEntity);
        HttpStatus status = (journalEntity != null)? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(journalEntity, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId id){
        JournalEntity journalEntity = journalEntityService.getById(id);
        if(journalEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        journalEntityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
