package net.m99.journalApp.controller;

import net.m99.journalApp.entity.UserEntity;
import net.m99.journalApp.service.UserEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity entity){
        try{
            return new ResponseEntity<>(userEntityService.save(entity), HttpStatus.CREATED);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userEntityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable ObjectId id){
        UserEntity entity = userEntityService.getById(id);
        HttpStatus status = (entity != null)? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(entity, status);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUserName(@PathVariable String username){
        UserEntity entity = userEntityService.findByUsername(username);
        HttpStatus status = (entity != null)? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(entity, status);
    }

    @PutMapping
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity newEntity){
        UserEntity entity = userEntityService.update(newEntity);
        HttpStatus status = (entity != null)? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(entity, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId id){
        UserEntity entity = userEntityService.getById(id);
        if(entity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userEntityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
