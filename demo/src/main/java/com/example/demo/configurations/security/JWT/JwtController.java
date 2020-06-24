package com.example.demo.configurations.security.JWT;

import javax.validation.Valid;

import com.example.demo.configurations.security.JpaUserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private JwtUtil jwtUtilService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {

        logger.info(jwtRequest.toString());

       try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
       }catch (BadCredentialsException e) {
           throw new Exception("Bad credentials!", e);
       }

       final UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

       logger.info(userDetails.toString());
       final String jwt = jwtUtilService.token(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));

    }
    
}
