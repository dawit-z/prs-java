package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.repository.UserRepository;
import com.maxtrain.bootcamp.prs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository uRepo;

    @GetMapping
    public ResponseEntity<Iterable<User>> findUsers() {
        var users = uRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable int id) {
        var user = uRepo.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
    }

    @GetMapping("{username}/{password}")
    public ResponseEntity<User> login(@PathVariable String username, @PathVariable String password){
        var user = uRepo.findByUsernameAndPassword(username, password);
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if(user == null || user.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var users = uRepo.save(user);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user == null || user.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var u = uRepo.findById(user.getId());
        if (u.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = uRepo.save(user);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        var user = uRepo.findById(id);
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        uRepo.delete(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    }


