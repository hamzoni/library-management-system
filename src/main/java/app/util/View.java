package app.util;

public class View {
	public interface Role {}
	public interface User extends Role {}
	
	public interface Ticket {}
	public interface TicketUser extends Ticket {};
	public interface TicketBook extends Ticket {};
	
	public interface BookDetail extends Book {}
	public interface Book {}
}
