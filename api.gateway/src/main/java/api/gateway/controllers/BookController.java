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
import api.gateway.versioning.ApiVersion;
import saga.share.dtos.BookDto;
import saga.share.dtos.TicketDto;
import saga.share.dtos.UserDto;
import saga.share.util.View;

@RestController
@RequestMapping("books")
@ApiVersion("1")
public class BookController {

	@JsonView(View.Ticket.class)
	@GetMapping("/{bookId}/borrowers")
	public List<UserDto> viewBorrowers(@PathVariable int bookId) {
		List<UserDto> borrowers = new ArrayList<UserDto>();
		return borrowers;
	}

	@JsonView(View.Ticket.class)
	@GetMapping("/{bookId}/tickets")
	public List<TicketDto> viewTickets(@PathVariable int bookId) {
		List<TicketDto> tickets = new ArrayList<TicketDto>();

		return tickets;
	}

	@PostMapping
	public Notification createBook(@RequestBody BookDto Book) {
		return new Notification(true, "Saved");
	}

	@PutMapping
	public Notification updateBook(@RequestBody BookDto Book) {
		return new Notification(true, "Saved");
	}

	@DeleteMapping("{id}")
	public Notification deleteBook(@PathVariable("id") int bookId) {
		return new Notification(true, "Done");
	}

	@GetMapping("{id}")
	public BookDto showBookDetail(@PathVariable("id") int bookId) {
		BookDto book = null;
		return book;
	}

	@JsonView(View.Book.class)
	@GetMapping
	public List<BookDto> listBook() {
		List<BookDto> books = new ArrayList<BookDto>();
		return books;
	}
}
