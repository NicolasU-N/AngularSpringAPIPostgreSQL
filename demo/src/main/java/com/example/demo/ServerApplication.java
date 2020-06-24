package com.example.demo;

import com.example.demo.models.service.IRolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	@Autowired
	IRolService rolService;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
