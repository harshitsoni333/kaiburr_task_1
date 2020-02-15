package com.example.servermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.servermanagement.exception.classes.ServerNotFound;
import com.example.servermanagement.model.Servers;
import com.example.servermanagement.repository.ServersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServersController {

    @Autowired
    private ServersRepository repo;

    @GetMapping("/servers")
    public List<Servers> getAllServers() {
        if(!repo.findAll().isEmpty()) return repo.findAll();
        else throw new ServerNotFound();
    }

    @GetMapping("/servers/{param}")
    public Servers getServer(@PathVariable("param") String param) {

        // finding by ID
        Servers server = repo.findById(param).orElse(null);

        if(server!=null) return server;
        else {
            // finding by Name
            server = repo.findByName(param).orElse(null);
            if(server!=null) return server;
            else throw new ServerNotFound();
        }
    }
    
    @DeleteMapping("servers/{id}")
    public void deleteServer(@PathVariable("id") String id) {
        Servers server = repo.findById(id).orElse(null);

        if(server!=null) {
            repo.deleteById(id);
        }
        else throw new ServerNotFound();
    }

    @PutMapping("/servers")
    public void putServer(@Valid @RequestBody Servers server) {
        Servers newServer = new Servers();
        
        newServer.setId(server.getId());
        newServer.setName(server.getName());
        newServer.setLanguage(server.getLanguage());
        newServer.setFramework(server.getFramework());

        repo.save(newServer);
    }

}