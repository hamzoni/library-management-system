package app.service;

import java.time.LocalDateTime;
import java.util.List;

import app.entity.Ticket;

/*
 * Use cases: 8, 9, 10, 11, 12, 13, 14
 */

public interface TicketService {
	
	// UC29
	List<Ticket> viewsExpiredTickets();
	
	// UC30
	List<Ticket> viewsExpiredTickets(Long userId);

	// UC11
	void borrow(long bookId, long userId)  throws Exception;

	// UC8
	void lend(long libraryId, LocalDateTime expireDate) ;
	
	// UC12
	void requestExtendBorrowDate(long libraryId);
	
	// UC9
	void extendBorrowDate(long libraryId, LocalDateTime expireDate);

	// UC23
	List<Ticket> viewRequestedBorrowTickets();
	
	// UC21
	List<Ticket> viewRequestedExtendTickets();

	// UC25
	List<Ticket> viewLendTickets();

}
