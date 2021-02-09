package com.thomasvitale.bookapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
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
@RequiredArgsConstructor
class BookController {
	private final BookRepository bookRepository;

	@GetMapping
	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@PostMapping
	public Mono<Book> addBookToCatalog(@RequestBody Book book) {
		return bookRepository.save(book);
	}
}

interface BookRepository extends ReactiveCrudRepository<Book,String> {}

@Data @AllArgsConstructor
class Book {
	@Id
	private String id;
	private String title;
}