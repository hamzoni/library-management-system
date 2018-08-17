package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import app.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>, JpaRepository<Role, Integer>, RoleRepositoryCustomized {
	Role findById(int roleId);
}
