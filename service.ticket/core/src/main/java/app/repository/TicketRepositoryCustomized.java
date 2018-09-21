package app.repository;

import java.util.List;

import app.entity.Ticket;

public interface TicketRepositoryCustomized {
	List<Ticket> getExpiredTickets();
}
