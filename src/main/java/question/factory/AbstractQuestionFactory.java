package question.factory;

import question.AbstractQuestion;
import question.builder.AbstractQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractQuestionFactory {
	protected AbstractQuestionBuilder questionBuilder;

	public abstract AbstractQuestion getQuestion();

	public AbstractQuestionBuilder getQuestionBuilder() {
		return questionBuilder;
	}

	public void setQuestionBuilder(AbstractQuestionBuilder questionBuilder) {
		this.questionBuilder = questionBuilder;
	}
}
