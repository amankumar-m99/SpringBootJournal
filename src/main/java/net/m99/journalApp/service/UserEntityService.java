package net.m99.journalApp.service;

import net.m99.journalApp.entity.JournalEntity;
import net.m99.journalApp.entity.UserEntity;
import net.m99.journalApp.repository.JournalRepository;
import net.m99.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity newUserEntity){
        UserEntity oldUserEntity = getById(newUserEntity.getId());
        if(oldUserEntity == null) {
            return null;
        }
        oldUserEntity.setUsername(newUserEntity.getUsername());
        oldUserEntity.setPassword(newUserEntity.getPassword());
        return userRepository.save(oldUserEntity);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity getById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
