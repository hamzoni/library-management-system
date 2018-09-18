package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.entity.Book;
import app.repository.BookRepository;
import app.service.BookService;
import app.util.Notification;
import app.util.View;

@RestController
@RequestMapping("books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	BookRepository bookRepo;

	@PostMapping
	public Notification createBook(@RequestBody Book Book) {
		bookService.create(Book);
		return new Notification(true, "Saved");
	}

	@PutMapping
	public Notification updateBook(@RequestBody Book Book) {
		bookService.update(Book);
		return new Notification(true, "Saved");
	}

	@DeleteMapping("{id}")
	public Notification deleteBook(@PathVariable("id") int bookId) {
		bookService.delete(bookId);
		return new Notification(true, "Done");
	}

	@GetMapping("{id}")
	public Book showBookDetail(@PathVariable("id") int bookId) {
		return bookService.show(bookId);
	}

	@JsonView(View.Book.class)
	@GetMapping
	public List<Book> listBook() {
		return bookService.list();
	}
}
