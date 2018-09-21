package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.User;
import app.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	public void create(User user) {
		userRepo.save(user);
	}

	@Override
	public void update(User user) {
		userRepo.save(user);
	}

	@Override
	public void delete(long userId) {

		User user = userRepo.findById(userId).get();
		userRepo.delete(user);

	}

	@Override
	public List<User> list() {
		return userRepo.findAll();
	}

	@Override
	public User show(long userId) {
		return userRepo.findById(userId).get();
	}

}
