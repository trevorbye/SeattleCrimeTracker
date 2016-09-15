package com.darigold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
@EntityScan(basePackages = "com.darigold.model")
@EnableAutoConfiguration
public class CrimeTrackerApplication {

	public static void main(String[] args) throws Exception{

		SpringApplication.run(CrimeTrackerApplication.class, args);

	}
}
