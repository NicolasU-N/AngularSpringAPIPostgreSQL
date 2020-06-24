package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Rol;

public interface IRolService {

    public List<Rol> findAll();

    public Rol findById(Long id);

    public Rol save(Rol rol);

    public Rol findByName(String name);

    public void deleteById(Long id);
    
}