package lms.shared.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lms.shared.util.View;

public class UserDto {

	@JsonView(View.User.class)
	private long id;

	@JsonView(View.User.class)
	private String username;

	@JsonView(View.User.class)
	private String email;

	@JsonIgnore
	private String password;

	@JsonView(View.UserDetail.class)
	private List<TicketDto> tickets; // borrowed books

	@JsonView(View.User.class)
	private List<RoleDto> roles;

	public UserDto() {
	}

	public UserDto(String username, String email, String password, List<RoleDto> roles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public List<BookDto> getBooks() {
		List<BookDto> books = new ArrayList<BookDto>();
		for (TicketDto ticket : tickets) {
			books.add(ticket.getBook());
		}
		return books;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TicketDto> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDto> tickets) {
		this.tickets = tickets;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

}