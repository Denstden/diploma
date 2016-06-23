package ua.kiev.unicyb.question.answers;

/**
 * @Author Denys Storozhenko.
 */
public class QuestionAnswers {
	private String[] answers;

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		String result = "";
		for (String answer : answers) {
			result += answer + "; ";
		}
		return result;
	}
}
