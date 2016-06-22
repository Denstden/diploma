package ua.kiev.unicyb.parser.config.variant;

import java.util.List;

import ua.kiev.unicyb.parser.config.question.QuestionData;

public class VariantConfig {
	private List<String> header;

	private List<String> footer;

	private Double points;

	private List<QuestionData> questions;

	public List<String> getHeader() {
		return header;
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}

	public List<String> getFooter() {
		return footer;
	}

	public void setFooter(List<String> footer) {
		this.footer = footer;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public List<QuestionData> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionData> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "VariantConfig{" +
				"header=" + header +
				", footer=" + footer +
				", points=" + points +
				", questions=" + questions +
				'}';
	}
}
