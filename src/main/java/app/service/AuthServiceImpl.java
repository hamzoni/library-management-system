package app.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.config.RoleConfig;
import app.dto.LoginDto;
import app.dto.UserDto;
import app.entity.Role;
import app.entity.User;
import app.exception.ExceptionDuplicateRecord;
import app.repository.RoleRepository;
import app.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	private boolean usernameExist(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) return true;
		return false;
	}

	private boolean emailExist(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) return true;
		return false;
	}
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User register(UserDto accountDto) throws ExceptionDuplicateRecord {

		if (emailExist(accountDto.getEmail())) {
			throw new ExceptionDuplicateRecord("There is an account with that email adress: " + accountDto.getEmail());
		}

		if (usernameExist(accountDto.getUsername())) {
			throw new ExceptionDuplicateRecord("There is an account with that username: " + accountDto.getUsername());
		}

		User user = new User();
		user.setEmail(accountDto.getEmail());
		user.setUsername(accountDto.getUsername());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		
		Role defaultRole = roleRepo.findOrCreate(RoleConfig.BORROWER);
		user.setRoles(Arrays.asList(defaultRole));
		
		return userRepo.save(user);
	}

	@Override
	public User login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
