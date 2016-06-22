package question.builder;

import parser.config.question.QuestionData;
import parser.config.question.question_types.AbstractQuestionConfigData;
import question.AbstractQuestion;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractQuestionBuilder {
	protected QuestionData questionData;
	protected AbstractQuestionConfigData configData;
	protected String globalPreamble;
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
