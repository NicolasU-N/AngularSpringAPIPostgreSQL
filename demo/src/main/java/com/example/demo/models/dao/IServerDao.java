package com.example.demo.models.dao;

import com.example.demo.models.entity.Server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IServerDao extends JpaRepository<Server, Long> {
    
}