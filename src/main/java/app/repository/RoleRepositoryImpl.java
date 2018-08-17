package app.repository;

import org.springframework.beans.factory.annotation.Autowired;

import app.entity.Role;

public class RoleRepositoryImpl implements RoleRepositoryCustomized {
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Role findOrCreate(int roleId) {
		Role role = roleRepo.findById(roleId);
		
		if (role == null) {
			role = new Role(Role.BORROWER);
			roleRepo.save(role);
		}
		
		return role;
	}
}
