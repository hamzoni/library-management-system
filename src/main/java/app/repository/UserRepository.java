package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
