package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.model.Requestline;
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
        recalculateRequestTotal(line.getRequest().getId());
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
        recalculateRequestTotal(line.getRequest().getId());
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteLine(@PathVariable int id) {
        var line = rlRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rlRepo.delete(line.get());
        var request = line.get().getRequest();
        recalculateRequestTotal(request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void recalculateRequestTotal(int requestId) {
        var req = rRepo.findById(requestId);
        if (req.isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return;
        }
        var request = req.get();
        var total = 0;
        for (var line : request.getRequestlines()) {
            total += line.getProduct().getPrice() * line.getQuantity();
        }
        request.setTotal(total);
        rRepo.save(request);
        new ResponseEntity<>(HttpStatus.OK);
    }

}
