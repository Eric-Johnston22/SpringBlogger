package com.ericjohnston;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ericjohnston")
@ComponentScan("controllers")
public class Cst323BlogsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cst323BlogsiteApplication.class, args);
		System.out.println("Hello World");
	}

}
