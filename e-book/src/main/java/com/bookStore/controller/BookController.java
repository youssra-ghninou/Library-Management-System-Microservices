package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		return bookService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/add")
	public Book createBook(@RequestBody Book book) {
		return bookService.save(book);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
		return bookService.findById(id)
				.map(book -> {
					book.setName(bookDetails.getName());
					book.setAuthor(bookDetails.getAuthor());
					book.setPrice(bookDetails.getPrice());
					return ResponseEntity.ok(bookService.save(book));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		if (bookService.findById(id).isPresent()) {
			bookService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
