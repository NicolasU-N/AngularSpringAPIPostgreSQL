package com.example.demo.models.dao;

import com.example.demo.models.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
    
}