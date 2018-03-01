package com.aurelius.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class WebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}
}
