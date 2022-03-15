package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.model.Request;
import com.maxtrain.bootcamp.prs.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/requests")
public class RequestController {

    @Autowired
    private RequestRepository rRepo;

    @GetMapping
    public ResponseEntity<Iterable<Request>> findRequests() {
        var requests = rRepo.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findRequest (@PathVariable int id) {
        var request = rRepo.findById(id);
        if (request.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(request.get(), HttpStatus.FOUND);
    }

    @GetMapping("review/{id}")
    public ResponseEntity<Iterable<Request>> findRequestsInReview(){
        var requests = rRepo.findByStatus("REVIEW");
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createRequest(@RequestBody Request request) {
        if(request == null || request.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var r = rRepo.save(request);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable int id, @RequestBody Request request) {
        if (request == null || request.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var r = rRepo.findById(request.getId());
        if (r.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = rRepo.save(request);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/review")
    public ResponseEntity<Object> review(@PathVariable int id, @RequestBody Request request) {
        var rev = rRepo.findById(id);
        if (rev.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var review = rev.get();
        if (review.getTotal() > 50){
            review.setStatus("REVIEW");
        }
        if(review.getTotal() <= 50){
            review.setStatus("APPROVED");
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("{id}/approve")
    public ResponseEntity<Request> approve(@PathVariable int id, @RequestBody Request request){
        request.setStatus("APPROVED");
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PutMapping("{id}/reject")
    public ResponseEntity<Request> reject(@PathVariable int id, @RequestBody Request request){
        request.setStatus("REJECTED");
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRequest(@PathVariable int id) {
        var request = rRepo.findById(id);
        if (request.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rRepo.delete(request.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
