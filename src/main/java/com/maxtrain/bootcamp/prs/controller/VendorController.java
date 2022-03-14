package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.model.Vendor;
import com.maxtrain.bootcamp.prs.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/vendors")
public class VendorController {

    @Autowired
    private VendorRepository vRepo;

    @GetMapping
    public ResponseEntity<Iterable<Vendor>> findVendors() {
        var vendors = vRepo.findAll();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> findVendor (@PathVariable int id) {
        var vendor = vRepo.findById(id);
        if (vendor.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vendor.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> createVendor(@RequestBody Vendor vendor) {
        if(vendor == null || vendor.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var v = vRepo.save(vendor);
        return new ResponseEntity<>(v, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable int id, @RequestBody Vendor vendor) {
        if (vendor == null || vendor.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var v = vRepo.findById(vendor.getId());
        if (v.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = vRepo.save(vendor);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteVendor(@PathVariable int id) {
        var vendor = vRepo.findById(id);
        if (vendor.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vRepo.delete(vendor.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
