package app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import app.util.View;

@Entity
public class User {

	/**
	 * 
	 */

	@JsonView(View.Ticket.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public final static class ROLE {
		public static final int BORROWER = 0;
		public static final int LIBRARIAN = 1;
	}

	@JsonView(View.Ticket.class)
	@Column(unique = true)
	private String username;

	@JsonView(View.Ticket.class)
	private String password;

	@JsonView(View.Ticket.class)
	@Column(name = "role", nullable = false, columnDefinition = "int(10) default " + ROLE.BORROWER)
	private int role = 0;

	@OneToMany(targetEntity = Ticket.class)
	@JoinColumn(name = "borrower_id")
	private List<Ticket> tickets; // borrowed books

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	public User() {
		super();
	}

	public User(String username, String password, int role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		for (Ticket ticket : tickets) {
			books.add(ticket.getBook());
		}
		return books;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> books) {
		this.tickets = books;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
