package io.pw.springrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("io.pw.springrent.controller")
public class SpringrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrentApplication.class, args);
	}

}
