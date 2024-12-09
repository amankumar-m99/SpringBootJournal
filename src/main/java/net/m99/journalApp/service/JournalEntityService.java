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

    public JournalEntity save(JournalEntity journalEntity){
        journalEntity.setDate(new Date());
        return journalRepository.save(journalEntity);
    }

    public JournalEntity update(JournalEntity newJournalEntity){
        JournalEntity oldJournalEntity = getById(newJournalEntity.getId());
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

    public List<JournalEntity> findAll(){
        return journalRepository.findAll();
    }

    public JournalEntity getById(ObjectId id){
        return journalRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id){
        journalRepository.deleteById(id);
    }
}
