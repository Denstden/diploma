package parser.config.question.question_types;

import java.util.List;

public class QuestionCheckboxConfigData extends AbstractQuestionConfigData {
	private Integer countAnswers;

	private Integer countCorrectAnswers;

	private Integer countIncorrectAnswers;

	private List<String> correctAnswers;

	private List<String> incorrectAnswers;

	public Integer getCountAnswers() {
		return countAnswers;
	}

	public void setCountAnswers(Integer countAnswers) {
		this.countAnswers = countAnswers;
	}

	public Integer getCountCorrectAnswers() {
		return countCorrectAnswers;
	}

	public void setCountCorrectAnswers(Integer countCorrectAnswers) {
		this.countCorrectAnswers = countCorrectAnswers;
	}

	public Integer getCountIncorrectAnswers() {
		return countIncorrectAnswers;
	}

	public void setCountIncorrectAnswers(Integer countIncorrectAnswers) {
		this.countIncorrectAnswers = countIncorrectAnswers;
	}

	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(List<String> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public List<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	@Override
	public String toString() {
		return "QuestionCheckboxConfigData{" +
				"hashtag=" + hashTag +
				", preamble=" + preamble +
				", countAnswers=" + countAnswers +
				", countCorrectAnswers=" + countCorrectAnswers +
				", countIncorrectAnswers=" + countIncorrectAnswers +
				", correctAnswers='" + correctAnswers + '\'' +
				", incorrectAnswers='" + incorrectAnswers + '\'' +
				", formatElements=" + formatElements +
				'}';
	}
}
