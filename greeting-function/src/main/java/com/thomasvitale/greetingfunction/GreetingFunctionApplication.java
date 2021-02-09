package com.thomasvitale.greetingfunction;

import java.util.function.Function;

import reactor.core.publisher.Flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GreetingFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingFunctionApplication.class, args);
	}

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}

	@Bean
	public Function<Flux<String>, Flux<String>> reverse() {
		return greeting -> greeting.map(g -> new StringBuilder(g).reverse().toString());
	}
}
