package question;

import java.util.Arrays;

import question.format.FormatSettings;

public abstract class AbstractQuestion {
	protected String preamble;

	protected String[] answers;

	protected FormatSettings formatSettings;

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

	public abstract void print();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		AbstractQuestion question1 = (AbstractQuestion) o;

		if (preamble != null ? !preamble.equals(question1.preamble) : question1.preamble != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = preamble != null ? preamble.hashCode() : 0;
		result = 31 * result + (answers != null ? Arrays.hashCode(answers) : 0);
		result = 31 * result + (formatSettings != null ? formatSettings.hashCode() : 0);
		return result;
	}
}
