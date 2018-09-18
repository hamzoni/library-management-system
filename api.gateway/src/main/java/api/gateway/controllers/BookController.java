package api.gateway.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import api.gateway.util.Notification;
import api.gateway.util.View;
import api.gateway.util.View.Book;
import api.gateway.util.View.Ticket;
import api.gateway.util.View.User;
import api.gateway.versioning.ApiVersion;

@RestController
@RequestMapping("books")
@ApiVersion("1")
public class BookController {

	
	@JsonView(View.Ticket.class)
	@GetMapping("/{bookId}/borrowers")
	public List<User> viewBorrowers(@PathVariable int bookId) {
		List<User> borrowers = new ArrayList<User>();
		return borrowers;
	}

	@JsonView(View.Ticket.class)
	@GetMapping("/{bookId}/tickets")
	public List<Ticket> viewTickets(@PathVariable int bookId) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		return tickets;
	}

	@PostMapping
	public Notification createBook(@RequestBody Book Book) {
		return new Notification(true, "Saved");
	}

	@PutMapping
	public Notification updateBook(@RequestBody Book Book) {
		return new Notification(true, "Saved");
	}

	@DeleteMapping("{id}")
	public Notification deleteBook(@PathVariable("id") int bookId) {
		return new Notification(true, "Done");
	}

	@GetMapping("{id}")
	public Book showBookDetail(@PathVariable("id") int bookId) {
		Book book = null;
		return book;
	}

	@JsonView(View.Book.class)
	@GetMapping
	public List<Book> listBook() {
		List<Book> books = new ArrayList<Book>();
		return books;
	}
}
