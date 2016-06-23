package ua.kiev.unicyb.question;

import java.util.Arrays;

import ua.kiev.unicyb.question.answers.QuestionAnswers;
import ua.kiev.unicyb.question.format.FormatSettings;

public abstract class AbstractQuestion {
	String preamble;

	String[] variantsOfAnswers;

	QuestionAnswers correctAnswers;

	FormatSettings formatSettings;

	static final int DEF_MAX_ANSWER_LENGTH = 15;

	public void setQuestion(String question) {
		this.preamble = question;
	}

	public void setVariantsOfAnswers(String[] variantsOfAnswers) {
		this.variantsOfAnswers = variantsOfAnswers;
	}

	public void setFormatSettings(FormatSettings formatSettings) {
		this.formatSettings = formatSettings;
	}

	public QuestionAnswers getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(QuestionAnswers correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public abstract String toString();

	public void print() {
		System.out.println(toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		AbstractQuestion question1 = (AbstractQuestion) o;

		return preamble != null ? preamble.equals(question1.preamble) : question1.preamble == null;
	}

	@Override
	public int hashCode() {
		int result = preamble != null ? preamble.hashCode() : 0;
		result = 31 * result + (variantsOfAnswers != null ? Arrays.hashCode(variantsOfAnswers) : 0);
		result = 31 * result + (formatSettings != null ? formatSettings.hashCode() : 0);
		return result;
	}
}
