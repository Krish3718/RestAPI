package com.example.RestApi;

import ch.qos.logback.core.hook.ShutdownHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(RestApiApplication.class, args);
	}

}
