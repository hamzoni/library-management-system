package lms.shared.dtos;

import com.fasterxml.jackson.annotation.JsonView;

import lms.shared.util.View;

public class RoleDto {

	public static final int ADMIN = 0;
	public static final int LIBRARIAN = 1;
	public static final int BORROWER = 2;

	public static final String[] TITLE = new String[] { "ADMIN", "LIBRARIAN", "BORROWER" };

	@JsonView(View.Role.class)
	private int id;

	@JsonView(View.Role.class)
	private String name;

	public RoleDto() {
	}

	public RoleDto(int id) {
		this.id = id;
		this.name = TITLE[id];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
