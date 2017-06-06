package ua.kiev.unicyb.question;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ua.kiev.unicyb.parser.config.variant.Estimation;
import ua.kiev.unicyb.question.answers.QuestionAnswers;
import ua.kiev.unicyb.question.format.FormatSettings;

@JsonTypeInfo(include= JsonTypeInfo.As.PROPERTY, use= JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractQuestion implements Serializable{
	String preamble;

	String[] variantsOfAnswers;

	@JsonIgnore
	QuestionAnswers correctAnswers;

	FormatSettings formatSettings;

	@JsonIgnore
	Estimation estimation;

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

	public String getPreamble() {
		return preamble;
	}

	public String[] getVariantsOfAnswers() {
		return variantsOfAnswers;
	}

	public FormatSettings getFormatSettings() {
		return formatSettings;
	}

	public Estimation getEstimation() {
		return estimation;
	}

	public void setEstimation(Estimation estimation) {
		this.estimation = estimation;
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
