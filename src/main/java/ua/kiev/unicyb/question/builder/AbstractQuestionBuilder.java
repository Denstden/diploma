package ua.kiev.unicyb.question.builder;

import ua.kiev.unicyb.parser.config.question.QuestionData;
import ua.kiev.unicyb.parser.config.question.question_types.AbstractQuestionConfigData;
import ua.kiev.unicyb.question.AbstractQuestion;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractQuestionBuilder {
	private QuestionData questionData;

	AbstractQuestionConfigData configData;

	String globalPreamble;

	public abstract AbstractQuestion build();

	public QuestionData getQuestionData() {
		return questionData;
	}

	public void setQuestionData(QuestionData questionData) {
		this.questionData = questionData;
	}

	public AbstractQuestionConfigData getConfigData() {
		return configData;
	}

	public void setConfigData(AbstractQuestionConfigData configData) {
		this.configData = configData;
	}

	public String getGlobalPreamble() {
		return globalPreamble;
	}

	public void setGlobalPreamble(String globalPreamble) {
		this.globalPreamble = globalPreamble;
	}
}
