package app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.api.ApiVersion;


@RestController
@RequestMapping("test")
@ApiVersion({"1", "1.1", "quy"})
public class TestController {

	@GetMapping
	public String test() {
		return "test";
	}
	
}
