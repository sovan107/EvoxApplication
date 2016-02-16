package com.evox.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.evox.web"})
public class UiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
}
