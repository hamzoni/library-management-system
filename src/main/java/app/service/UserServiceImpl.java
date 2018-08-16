package app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.dto.UserDto;
import app.entity.Book;
import app.entity.Ticket;
import app.entity.User;
import app.exception.ExceptionDuplicateRecord;
import app.exception.ExceptionRecordNotFound;
import app.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserRepository userRepo;

	public void create(User user) {

		// check if username is duplicated
		userRepo.save(user);

	}

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
		user.setPassword(accountDto.getPassword());
		return userRepo.save(user);
	}

	private boolean usernameExist(String username) {
		User user = userRepo.findByUsername(username);

		if (user != null) {
			return true;
		}

		return false;

	}

	private boolean emailExist(String email) {

		User user = userRepo.findByEmail(email);

		if (user != null) {
			return true;
		}

		return false;

	}

	@Override
	public void update(User user) {
		userRepo.save(user);
	}

	@Override
	public void delete(long userId) {

		if (!userRepo.existsById(userId))
			throw new ExceptionRecordNotFound();

		User user = userRepo.findById(userId).get();
		userRepo.delete(user);

	}

	@Override
	public List<User> list() {

		List<User> list = new ArrayList<User>();

		userRepo.findAll().iterator().forEachRemaining(list::add);

		return list;

	}

	@Override
	public User show(long userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public List<Book> viewBorrowedBooks(long borrowerId) {

		// check if user is available
		if (!userRepo.existsById(borrowerId))
			throw new ExceptionRecordNotFound();

		User borrower = userRepo.findById(borrowerId).get();
		return borrower.getBooks();
	}

	@Override
	public List<Ticket> viewBorrowTickets(long borrowerId) {
		// check if user is available
		if (!userRepo.existsById(borrowerId))
			throw new ExceptionRecordNotFound();

		User borrower = userRepo.findById(borrowerId).get();
		return borrower.getTickets();
	}

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Object> findAll() {
		List<Object> list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

}
