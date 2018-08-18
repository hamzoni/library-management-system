package app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import app.config.AuthorizationServerConfig;
import app.dto.LoginDto;
import app.dto.UserDto;
import app.service.AuthService;
import app.util.Notification;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Value("${spring.data.rest.base-path}")
    private String BASE_URL;

	@PostMapping(value = "/register")
	public Notification signUp(@Valid @RequestBody UserDto accountDto) {

		authService.register(accountDto);
		return new Notification(true, "Created.");
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto) {
		
		// DEFINE AUTHORIZATION SERVER URL
		String url = BASE_URL + "/oauth/token";

		// SET HEADERS AND REQUEST BODY
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", loginDto.getUsername());
		map.add("password", loginDto.getPassword());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		RestTemplate restTemplate = new RestTemplate();

		// BASIC AUTH
		String clientId = AuthorizationServerConfig.CLIENT_ID;
		String clientSecret = AuthorizationServerConfig.SECRET;

		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(clientId, clientSecret));
		
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		return response;
	}

}
