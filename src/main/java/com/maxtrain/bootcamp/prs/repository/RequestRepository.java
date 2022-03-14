package com.maxtrain.bootcamp.prs.repository;

import com.maxtrain.bootcamp.prs.model.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    Iterable<Request> findByStatus(String status);
}
