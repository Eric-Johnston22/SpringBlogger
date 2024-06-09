package com.ericjohnston;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ericjohnston")
@ComponentScan("controllers")
@ComponentScan("data")
@ComponentScan("mapper")
@ComponentScan("models")
@ComponentScan("repository")
@ComponentScan("services")
public class Cst323BlogsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cst323BlogsiteApplication.class, args);
		System.out.println("Build success! Application running @ localhost:8080 🌍");
		// Log environment variables for debugging
        System.out.println("DB URL: " + System.getenv("MYSQLCONNSTR_DB_URL"));
        System.out.println("DB Username: " + System.getenv("MYSQLCONNSTR_DB_USERNAME"));
        System.out.println("DB Password: " + System.getenv("MYSQLCONNSTR_DB_PASSWORD"));
	}

}
