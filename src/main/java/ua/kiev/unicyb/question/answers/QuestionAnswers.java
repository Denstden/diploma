package ua.kiev.unicyb.question.answers;

/**
 * @Author Denys Storozhenko.
 */
public class QuestionAnswers {
	private String[] questionAnswers;

	public String[] getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(String[] questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	@Override
	public String toString() {
		String result = "";
		for (String answer : questionAnswers){
			result += answer + "; ";
		}
		return result;
	}
}
