package com.thomasvitale.bookapp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApplication.class, args);
	}
}

@RestController
@RequestMapping("books")
class BookController {
	private static final Map<String,Book> bookCatalog = new ConcurrentHashMap<>();

	@GetMapping
	public Flux<Book> getAllBooks() {
		return Flux.fromIterable(bookCatalog.values());
	}

	@PostMapping
	public Mono<Book> addBookToCatalog(@RequestBody Book book) {
		bookCatalog.put(book.getId(), book);
		return Mono.just(book);
	}
}

@Data @AllArgsConstructor
class Book {
	private String id;
	private String title;
}
