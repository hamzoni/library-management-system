package app.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import app.entity.Ticket;
import saga.share.util.DataUtil;

public class TicketRepositoryImpl implements TicketRepositoryCustomized {
	

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Ticket> getExpiredTickets() {
		
		LocalDateTime now = LocalDateTime.now();
		
		String query = "FROM Ticket e WHERE e.expireDate < :now";
		
		List<Ticket> tickets = DataUtil.castList(Ticket.class, em
			.createQuery(query)
			.setParameter("now", now)
			.getResultList());
		
		return tickets;
	}

}
