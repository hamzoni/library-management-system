package saga.share.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

import saga.share.util.View;

public class TicketDto {

	public static class REQUEST {
		public static int NONE = 0;
		public static int EXTEND = 1; // request extend borrow date
		public static int BORROW = 2; // request borrow book
	}
	
	@JsonView(View.Ticket.class)
	private long id;

	@JsonView(View.TicketUser.class)
	private BookDto book;

	@JsonView(View.TicketBook.class)
	private UserDto borrower;

	@JsonView(View.Ticket.class)
	private LocalDateTime borrowDate = LocalDateTime.now();

	@JsonView(View.Ticket.class)
	private LocalDateTime expireDate;

	@JsonView(View.Ticket.class)
	private LocalDateTime extendDate; // last borrow date

	@JsonView(View.Ticket.class)
	private int action = TicketDto.REQUEST.NONE;

	public TicketDto() {
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public UserDto getBorrower() {
		return borrower;
	}

	public void setBorrower(UserDto borrower) {
		this.borrower = borrower;
	}

	public LocalDateTime getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDateTime borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public LocalDateTime getExtendDate() {
		return extendDate;
	}

	public void setExtendDate(LocalDateTime extendDate) {
		this.extendDate = extendDate;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}