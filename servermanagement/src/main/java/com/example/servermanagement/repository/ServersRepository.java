package com.example.servermanagement.repository;

import java.util.List;
import java.util.Optional;

import com.example.servermanagement.model.Servers;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServersRepository extends MongoRepository<Servers, String> {

    public List<Servers> findAll();
    public Optional<Servers> findByName(String name);
}