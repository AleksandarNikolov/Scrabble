package exceptions;

@SuppressWarnings("serial")
public class NoTilesInBagException extends Exception {
	public NoTilesInBagException(String message) {
		super(message);
	}
}
