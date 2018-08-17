package app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.LoginDto;
import app.dto.UserDto;
import app.service.AuthService;
import app.util.Notification;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(value = "/register")
	public Notification signUp(@Valid @RequestBody UserDto accountDto) {

		authService.register(accountDto);
		return new Notification(true, "Created.");
	}

	@PostMapping(value = "/login")
	public Notification signIn(@RequestBody LoginDto loginDto) {
		return new Notification(true, "Login.");
	}

}
