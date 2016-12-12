package kickoff.exception;

public class NoSuchDbTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchDbTypeException(String message) {
		super(message);
	}

}
