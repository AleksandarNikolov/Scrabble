package exceptions;

@SuppressWarnings("serial")
public class AlreadyPlacedException extends Exception {
	public AlreadyPlacedException (String message) {
		super(message);
	}
}
