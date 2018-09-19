package lms.shared.dtos;

import com.fasterxml.jackson.annotation.JsonView;

import lms.shared.util.View;

public class BookDto {
	@JsonView(View.Book.class)
	private long id;
	
	@JsonView(View.Book.class)
	private String name;
	
	@JsonView(View.Book.class)
	private int stock;
	
	public BookDto() {
	}

	public long getId() {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
