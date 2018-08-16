package app.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;

import app.util.View;

@Entity
public class User implements UserDetails {

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
	@Column(unique = true)
	private String email;

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

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		for (Ticket ticket : tickets) {
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

}
