package com.example.demo.configurations.security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.configurations.security.JpaUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private JwtUtil jwtUtilService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        final String header = request.getHeader(JwtConstants.HEADER);

        response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, POST, UPDATE");

        String username = null;
        String jwt = null;

        if (header != null && header.startsWith(JwtConstants.TOKEN_HEADER)) {

            jwt = header.substring(JwtConstants.TOKEN_HEADER.length() + 1);
            username = jwtUtilService.username(jwt);

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);

            if (jwtUtilService.validateToken(jwt, userDetails)) {


                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),null ,userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                logger.info(usernamePasswordAuthenticationToken.toString());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                

            }

        }

        filterChain.doFilter(request, response);

    }

}