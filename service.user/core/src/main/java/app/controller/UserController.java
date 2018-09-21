package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.entity.User;
import app.service.UserService;
import saga.share.api.ApiVersion;
import saga.share.util.Notification;
import saga.share.util.View;

@RestController
@ApiVersion("1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public Notification createUser(@RequestBody User user) {
		userService.create(user);
		return new Notification(true, "Saved");
	}

	@PutMapping
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
	
	@GetMapping
	@JsonView(View.User.class)
	public List<User> listUsers() {
		return userService.list();
	}
	
}
