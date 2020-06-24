package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Server;

public interface IServerService{

    public List<Server> findAll();

    public Server findById(Long id);

    public Server save(Server server);

    public void deleteById(Long id);
    
}