package com.example.demo.configurations.run;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.models.entity.Rol;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IRolService;
import com.example.demo.models.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Set<Rol> roles = new HashSet<Rol>(Set.of(new Rol("admin"), new Rol("user")));
     
        roles.forEach(rol -> rolService.save(rol));

        Usuario usuario = new Usuario("USERNAME", "password", roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioService.save(usuario);

    }

}