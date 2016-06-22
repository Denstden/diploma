package ua.kiev.unicyb.exception;

/**
 * @Author Denys Storozhenko.
 */
public class EmptyFolderException extends Exception {
	@Override
	public String getMessage() {
		return "Folder is empty.";
	}
}
