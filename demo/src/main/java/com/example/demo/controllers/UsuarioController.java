package com.example.demo.controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.example.demo.models.entity.Rol;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IRolService;
import com.example.demo.models.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UsuarioController {

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/usuarios/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {

        Usuario usuario = usuarioService.findByUsername(username);

        HttpStatus httpStatus = usuario != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(usuario, httpStatus);

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Usuario usuario = usuarioService.findById(id);

        HttpStatus httpStatus = usuario != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(usuario, httpStatus);

    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> save(@Valid @RequestBody Usuario usuario) {

        Set<Rol> roles = new HashSet<Rol>(Set.of(rolService.findByName("USER")));

        System.out.println(roles);

        usuario.setRoles(roles);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioService.save(usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {

        Usuario updateUsuario = usuarioService.findById(id);

        if (updateUsuario != null) {

            updateUsuario.setUsername(usuario.getUsername());
            updateUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            updateUsuario.setEnable(usuario.isEnable());
            updateUsuario.setServers(usuario.getServers());
            updateUsuario.setRoles(usuario.getRoles());
            updateUsuario.setUpdateAt(new Date());

            usuarioService.save(updateUsuario);

            return new ResponseEntity<>(updateUsuario, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        usuarioService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
