package com.example.demo.models.dao;

import com.example.demo.models.entity.Rol;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolDao extends JpaRepository<Rol, Long>{
    
    public Rol findByName(String name); 
}