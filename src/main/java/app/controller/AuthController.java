package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.UserDto;
import app.entity.User;
import app.exception.ExceptionDuplicateRecord;
import app.service.UserService;
import app.util.Notification;

@RestController
@RequestMapping("**/auth")
public class AuthController implements BaseController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/signup")
	public Notification signup(@RequestBody UserDto accountDto, BindingResult result) {
		User account = new User();
		if (!result.hasErrors()) {
			account = createUserAccount(accountDto);
		}
		if (account == null) {
			return new Notification(false, "Create account false");
		}

		return new Notification(true, "Created.");
	}

	private User createUserAccount(UserDto accountDto) {
		User account = null;
		try {
			account = userService.register(accountDto);
		} catch (ExceptionDuplicateRecord e) {
			return null;
		}
		return account;
	}

}
