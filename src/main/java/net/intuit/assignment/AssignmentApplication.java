package net.intuit.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication()
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
