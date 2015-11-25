package domain;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbException(String message, Throwable e) {
		super(message, e);
	}

	public DbException(String message) {
		super(message);
	}

	public DbException() {
		super();
	}
}
