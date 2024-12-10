package net.m99.journalApp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.m99.journalApp.entity.JournalEntity;
import net.m99.journalApp.entity.UserEntity;
import net.m99.journalApp.repository.JournalRepository;

@Service
public class UserJournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private JournalEntityService journalEntityService;

    public UserEntity saveUserJournal(String username, JournalEntity journalEntity){
    	UserEntity userEntity = userEntityService.findByUsername(username);
    	if(userEntity == null) {
    		return null;
    	}
        userEntity.getJournalEntities().add(journalRepository.save(journalEntity));
        return userEntityService.save(userEntity);
    }

    public List<JournalEntity> findAllJournals(String username){
    	UserEntity userEntity = userEntityService.findByUsername(username);
    	if(userEntity == null) {
    		return null;
    	}
        return userEntity.getJournalEntities();
    }

	public Boolean deleteJournal(String username, ObjectId id) {
		UserEntity userEntity = userEntityService.findByUsername(username);
		if(userEntity == null) {
			return false;
		}
		JournalEntity journalEntity = journalEntityService.getById(id);
		if(journalEntity == null) {
			return false;
		}
		userEntity.getJournalEntities().remove(journalEntity);
		userEntityService.save(userEntity);
		journalEntityService.deleteById(id);
		return true;
	}
}
