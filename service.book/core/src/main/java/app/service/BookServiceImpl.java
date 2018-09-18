package app.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import app.entity.Book;
import app.repository.BookRepository;
import lms.shared.exceptions.ExceptionDuplicateRecord;
import lms.shared.exceptions.ExceptionRecordNotFound;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepo;

	public void create(Book book) {

		// check if book's name is duplicated
		try {
			bookRepo.save(book);
		} catch (ConstraintViolationException e) {
			throw new ExceptionDuplicateRecord();
		}

	}

	@Override
	public void update(Book book) {
		bookRepo.save(book);
	}

	@Override
	public void delete(long bookId) {

		if (!bookRepo.existsById(bookId))
			throw new ExceptionRecordNotFound();

		Book book = bookRepo.findById(bookId).get();
		bookRepo.delete(book);

	}

	@Override
	public List<Book> list() {
		return bookRepo.findAll();
	}

	@Override
	public Book show(long bookId) {
		return bookRepo.findById(bookId).get();
	}


	@Override
	public Page<Book> paginate(Pageable pageable) {
		return bookRepo.findAll(pageable);
	}
}
