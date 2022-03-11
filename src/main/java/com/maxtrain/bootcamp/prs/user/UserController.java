package com.maxtrain.bootcamp.prs.user;

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
    public ResponseEntity<Iterable<User>> getUsers() {
        var users = uRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        var user = uRepo.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        if(user == null || user.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var users = uRepo.save(user);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }









}
