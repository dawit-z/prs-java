package com.maxtrain.bootcamp.prs.repository;

import com.maxtrain.bootcamp.prs.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
