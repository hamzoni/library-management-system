package app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

import app.config.RoleConfig;
import app.util.View;

@Entity
public class Role {

	@Id
	private int id;

	@Column(unique = true)
	private String name;

	@JsonView(View.Ticket.class)
	@ManyToMany( targetEntity=User.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List users;

	public Role() {
		super();
	}

	public Role(int id) {
		super();
		this.id = id;
		this.name = RoleConfig.TITLE[id];
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
