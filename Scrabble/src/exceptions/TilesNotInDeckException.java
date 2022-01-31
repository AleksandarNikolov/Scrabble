package exceptions;

@SuppressWarnings("serial")
public class TilesNotInDeckException extends Exception {
	public TilesNotInDeckException(String message) {
		super(message);
	}
}
