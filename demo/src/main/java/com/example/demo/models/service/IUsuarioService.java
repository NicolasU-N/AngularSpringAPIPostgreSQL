package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Usuario;

public interface IUsuarioService {

    public List<Usuario> findAll();

    public Usuario findById(Long id);

    public Usuario save(Usuario usuario);

    public Usuario findByUsername(String username);

    public void deleteById(Long id);

}