package com.evox.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class UiApplication {

	
	@Value("${org.sample.sampleProperty}")
    private String sampleProperty;

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
	
	 @PostConstruct
	    public void postConstruct() {
	        System.out.print("SampleApplication started: " + sampleProperty);
	    }
}
