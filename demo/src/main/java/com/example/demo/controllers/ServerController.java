package com.example.demo.controllers;

import java.util.Date;

import javax.validation.Valid;

import com.example.demo.models.entity.Server;
import com.example.demo.models.service.IServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ServerController {

    @Autowired
    private IServerService serverService;

    @GetMapping("/servers")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(serverService.findAll(), HttpStatus.OK);

    }


    @GetMapping("/servers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Server server = serverService.findById(id);

        HttpStatus httpStatus = server != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(server, httpStatus);

    }

    @PostMapping("/servers")
    public ResponseEntity<?> save(@Valid @RequestBody Server server) {

        
        return new ResponseEntity<>(serverService.save(server), HttpStatus.OK);

        
        

    }

    @PutMapping("/servers/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Server server, @PathVariable Long id) {

        Server updateServer = serverService.findById(id);

        if (updateServer != null) {        

        updateServer.setDns(server.getDns());
        updateServer.setIp(server.getIp());
        updateServer.setPrice(server.getPrice());
        updateServer.setCapacity(server.getCapacity());
        updateServer.setUpdateAt(new Date());

        serverService.save(updateServer);

        return new ResponseEntity<>(updateServer, HttpStatus.CREATED);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/servers/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        serverService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
