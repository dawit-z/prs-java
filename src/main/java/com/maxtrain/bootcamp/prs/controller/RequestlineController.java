package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.model.Product;
import com.maxtrain.bootcamp.prs.model.Request;
import com.maxtrain.bootcamp.prs.model.Requestline;
import com.maxtrain.bootcamp.prs.repository.ProductRepository;
import com.maxtrain.bootcamp.prs.repository.RequestRepository;
import com.maxtrain.bootcamp.prs.repository.RequestlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/requestlines")
public class RequestlineController {

    @Autowired
    private RequestlineRepository rlRepo;

    @Autowired
    private RequestRepository rRepo;

//    private ResponseEntity calculateRequest(int requestId) {
//        var req = rRepo.findById(requestId);
//        if (req.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        var request = req.get();
//
//
//    }

    @GetMapping
    public ResponseEntity<Iterable<Requestline>> findRequestlines() {
        var lines = rlRepo.findAll();
        return new ResponseEntity<>(lines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requestline> findRequestline (@PathVariable int id) {
        var line = rlRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(line.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> createLine(@RequestBody Requestline line) {
        if(line == null || line.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var rl = rlRepo.save(line);
        return new ResponseEntity<>(rl, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Requestline> updateLine(@PathVariable int id, @RequestBody Requestline line) {
        if (line == null || line.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var rl = rlRepo.findById(line.getId());
        if (rl.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = rlRepo.save(line);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteLine(@PathVariable int id) {
        var line = rlRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rlRepo.delete(line.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
