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
import app.entity.Ticket;
import app.entity.User;
import app.lms.ApiVersionControl;
import app.service.UserService;
import app.util.Notification;
import app.util.View;

@RestController
@ApiVersionControl
@RequestMapping("**/user")
public class UserController implements BaseController {

	@Autowired
	private UserService userService;
	
	@JsonView(View.Ticket.class)
	@GetMapping("/{userId}/books")
	public List<Book> viewBooks(@PathVariable long userId) {
		return userService.viewBorrowedBooks(userId);
	}
	
	@JsonView(View.Ticket.class)
	@GetMapping("/{userId}/tickets")
	public List<Ticket> viewTickets(@PathVariable long userId) {
		return userService.viewBorrowTickets(userId);
	}

	@PostMapping()
	public Notification createUser(@RequestBody User user) {
		userService.create(user);
		return new Notification(true, "Saved");
	}

	@PutMapping()
	public Notification updateUser(@RequestBody User user) {
		userService.update(user);
		return new Notification(true, "Saved");
	}
	
	@DeleteMapping("{id}")
	public Notification deleteUser(@PathVariable("id") int userId) {
		userService.delete(userId);
		return new Notification(true, "Done");
	}
	
	
	@GetMapping("{id}")
	public User showUserInfo(@PathVariable("id") int userId) {
		return userService.show(userId);
	}
	
	@GetMapping()
	@JsonView(View.Ticket.class)
	public List<User> listUsers() {
		return userService.list();
	}
	
}
