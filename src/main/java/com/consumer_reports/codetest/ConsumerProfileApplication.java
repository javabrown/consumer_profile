package com.consumer_reports.codetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ConsumerProfileApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ConsumerProfileApplication.class, args);
	}
}
