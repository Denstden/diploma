package ua.kiev.unicyb.exception;

/**
 * @Author Denys Storozhenko.
 */
public class UnsupportedQuestionTypeException extends Exception {
	@Override
	public String getMessage() {
		return "Unsupported type of question in config.xml";
	}
}
