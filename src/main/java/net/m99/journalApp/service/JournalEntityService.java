package net.m99.journalApp.service;

import net.m99.journalApp.entity.JournalEntity;
import net.m99.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JournalEntityService {

    @Autowired
    private JournalRepository journalRepository;

    public JournalEntity saveJournal(JournalEntity journalEntity){
        journalEntity.setDate(new Date());
        return journalRepository.save(journalEntity);
    }

    public JournalEntity updateJournal(JournalEntity newJournalEntity){
        JournalEntity oldJournalEntity = getJournalById(newJournalEntity.getId());
        if(oldJournalEntity == null){
            return null;
        }
        if(newJournalEntity.getTitle() != null && !newJournalEntity.getTitle().isEmpty()){
            oldJournalEntity.setTitle(newJournalEntity.getTitle());
        }
        if(newJournalEntity.getContent() != null && !newJournalEntity.getContent().isEmpty()){
            oldJournalEntity.setContent(newJournalEntity.getContent());
        }
        return journalRepository.save(oldJournalEntity);
    }

    public List<JournalEntity> getAllJournals(){
        return journalRepository.findAll();
    }

    public JournalEntity getJournalById(ObjectId id){
        return journalRepository.findById(id).orElse(null);
    }

    public void deleteJournalById(ObjectId id){
        journalRepository.deleteById(id);
    }
}
