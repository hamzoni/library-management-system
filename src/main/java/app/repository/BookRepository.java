package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
}
