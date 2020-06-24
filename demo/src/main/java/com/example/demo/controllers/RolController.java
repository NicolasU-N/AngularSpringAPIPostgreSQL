package com.example.demo.controllers;

import java.util.Date;

import javax.validation.Valid;

import com.example.demo.models.entity.Rol;
import com.example.demo.models.service.IRolService;

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
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/roles")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>( rolService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Rol rol = rolService.findById(id);

        HttpStatus httpStatus = rol != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(rol, httpStatus);

    }

    @PostMapping("/roles")
    public ResponseEntity<?> save(@Valid @RequestBody Rol rol) {

        return new ResponseEntity<>(rolService.save(rol), HttpStatus.OK);

    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Rol rol, @PathVariable Long id) {

        Rol updateRol = rolService.findById(id);

        if (updateRol != null) {

            updateRol.setName(rol.getName());
            updateRol.setUpdateAt(new Date());

            rolService.save(updateRol);

            return new ResponseEntity<>(updateRol, HttpStatus.CREATED);

        } 

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        rolService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
