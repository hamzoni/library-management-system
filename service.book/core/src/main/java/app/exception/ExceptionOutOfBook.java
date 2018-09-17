package app.exception;

public class ExceptionOutOfBook extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionOutOfBook() {
		super("Intended books is out of stock");
	}

	public ExceptionOutOfBook(String exception) {
		super("The intended book is out of stock: " + exception);
	}
}
