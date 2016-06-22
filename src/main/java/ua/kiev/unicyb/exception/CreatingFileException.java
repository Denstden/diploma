package ua.kiev.unicyb.exception;

/**
 * @Author Denys Storozhenko.
 */
public class CreatingFileException extends Exception {
	@Override
	public String getMessage() {
		return "File didn't created.";
	}
}
