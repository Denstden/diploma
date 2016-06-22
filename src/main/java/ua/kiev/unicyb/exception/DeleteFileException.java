package ua.kiev.unicyb.exception;

/**
 * @Author Denys Storozhenko.
 */
public class DeleteFileException extends Exception {
	@Override
	public String getMessage() {
		return "Deleting file error.";
	}
}
