package com.maxtrain.bootcamp.prs.controller;

import com.maxtrain.bootcamp.prs.model.Product;
import com.maxtrain.bootcamp.prs.model.User;
import com.maxtrain.bootcamp.prs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository pRepo;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findProducts() {
        var products = pRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct (@PathVariable int id) {
        var product = pRepo.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if(product == null || product.getId() != 0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        var p = pRepo.save(product);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (product == null || product.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var p = pRepo.findById(product.getId());
        if (p.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = pRepo.save(product);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        var product = pRepo.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pRepo.delete(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
