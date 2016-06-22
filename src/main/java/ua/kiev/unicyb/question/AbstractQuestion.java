package ua.kiev.unicyb.question;

import java.util.Arrays;

import ua.kiev.unicyb.question.format.FormatSettings;

public abstract class AbstractQuestion {
	String preamble;

	String[] answers;

	FormatSettings formatSettings;

	public void setQuestion(String question) {
		this.preamble = question;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public void setFormatSettings(FormatSettings formatSettings) {
		this.formatSettings = formatSettings;
	}

	public abstract String toString();

	public void print(){
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
		result = 31 * result + (answers != null ? Arrays.hashCode(answers) : 0);
		result = 31 * result + (formatSettings != null ? formatSettings.hashCode() : 0);
		return result;
	}
}
