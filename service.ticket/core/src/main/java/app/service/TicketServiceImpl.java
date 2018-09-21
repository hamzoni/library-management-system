package app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Ticket;
import app.exception.ExceptionInvalidBusinessProcess;
import app.exception.ExceptionInvalidParam;
import app.exception.ExceptionRecordNotFound;
import app.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepo;


	@Override
	public void lend(long libraryId, LocalDateTime expireDate) {

		// check if ticket is available
		if (!ticketRepo.existsById(libraryId))
			throw new ExceptionRecordNotFound();

		Ticket ticket = ticketRepo.findById(libraryId).get();

		// ticket must be in BORROW process
		if (ticket.getAction() != Ticket.REQUEST.BORROW) {
			throw new ExceptionInvalidBusinessProcess("User has already borrowed this book");
		}

		ticket.setAction(Ticket.REQUEST.NONE);

		// set expire date for book
		int borrowDays = 30;
		LocalDateTime dateToExpire = LocalDateTime.now().plusDays(borrowDays);
		ticket.setExpireDate(dateToExpire);
		ticketRepo.save(ticket);
	}

	@Override
	public void requestExtendBorrowDate(long libraryId) {
		// check if ticket is available
		if (!ticketRepo.existsById(libraryId))
			throw new ExceptionRecordNotFound();

		Ticket ticket = ticketRepo.findById(libraryId).get();

		// ticket must be in NONE process
		if (ticket.getAction() == Ticket.REQUEST.BORROW) {
			throw new ExceptionInvalidBusinessProcess("Book is in borrowing process");
		}
		if (ticket.getAction() == Ticket.REQUEST.EXTEND) {
			throw new ExceptionInvalidBusinessProcess("Book is already in process of extending");
		}

		ticket.setAction(Ticket.REQUEST.EXTEND);
		ticketRepo.save(ticket);

	}

	@Override
	public void extendBorrowDate(long libraryId, LocalDateTime expireDate) {
		// check if ticket is available
		if (!ticketRepo.existsById(libraryId))
			throw new ExceptionRecordNotFound();

		// check if expire date is valid
		if (expireDate.isBefore(LocalDateTime.now())) {
			throw new ExceptionInvalidParam("Expire date must be later than now");
		}

		Ticket ticket = ticketRepo.findById(libraryId).get();
		
		// ticket must be in EXTEND process
		if (ticket.getAction() != Ticket.REQUEST.EXTEND) {
			throw new ExceptionInvalidBusinessProcess("No extend request found");
		}

		ticket.setExpireDate(expireDate);
		ticket.setExtendDate(LocalDateTime.now());
		ticket.setAction(Ticket.REQUEST.NONE);

		ticketRepo.save(ticket);
	}

	@Override
	public List<Ticket> viewLendTickets() {
		return ticketRepo.findTicketByActivity(Ticket.REQUEST.NONE);
	}

	@Override
	public List<Ticket> viewRequestedBorrowTickets() {
		return ticketRepo.findTicketByActivity(Ticket.REQUEST.BORROW);
	}

	@Override
	public List<Ticket> viewRequestedExtendTickets() {
		return ticketRepo.findTicketByActivity(Ticket.REQUEST.EXTEND);
	}

	@Override
	public List<Ticket> viewsExpiredTickets() {
		return ticketRepo.getExpiredTickets();
	}

	@Override
	public void borrow(long bookId, long userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ticket> viewsExpiredTickets(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
