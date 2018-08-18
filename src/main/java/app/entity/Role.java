package app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

import app.util.View;

@Entity
public class Role {
	
	public static final int ADMIN = 0;
	public static final int LIBRARIAN = 1;
	public static final int BORROWER = 2;
	
	public static final String[] TITLE = new String[] {
		"ADMIN", "LIBRARIAN", "BORROWER"
	};

	@JsonView(View.Ticket.class)
	@Id
	private int id;

	@JsonView(View.Ticket.class)
	@Column(unique = true)
	private String name;

	@ManyToMany( targetEntity=User.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<User> users;

	public Role() {
		super();
	}

	public Role(int id) {
		super();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
