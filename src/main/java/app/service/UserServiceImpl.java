package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Book;
import app.entity.Ticket;
import app.entity.User;
import app.exception.ExceptionRecordNotFound;
import app.repository.UserRepository;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	public void create(User user) {

		// check if username is duplicated
		userRepo.save(user);

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

}
