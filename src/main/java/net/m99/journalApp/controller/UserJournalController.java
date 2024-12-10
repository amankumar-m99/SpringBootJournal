package net.m99.journalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.m99.journalApp.entity.JournalEntity;
import net.m99.journalApp.entity.UserEntity;
import net.m99.journalApp.service.UserJournalService;

@RestController
@RequestMapping("/user-journal")
public class UserJournalController {

    @Autowired
    private UserJournalService userJournalService;

    @PostMapping("{username}")
    public ResponseEntity<UserEntity> addJournal(@RequestBody JournalEntity journalEntity, @PathVariable String username){
        try{
        	UserEntity userEntity = userJournalService.saveUserJournal(username, journalEntity);
        	HttpStatus status = (userEntity != null) ? HttpStatus.CREATED : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(userEntity, status);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<JournalEntity>> getAllJournals(@PathVariable String username) {
    	List<JournalEntity> journalEntities = userJournalService.findAllJournals(username);
    	HttpStatus status = (journalEntities != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(journalEntities, status);
    }

    @DeleteMapping("{username}/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable String username, @PathVariable ObjectId id){
    	try{
        	Boolean isDeleted = userJournalService.deleteJournal(username, id);
        	HttpStatus status = (isDeleted) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
