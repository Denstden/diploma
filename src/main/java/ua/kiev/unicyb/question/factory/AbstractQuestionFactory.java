package ua.kiev.unicyb.question.factory;

import ua.kiev.unicyb.question.AbstractQuestion;
import ua.kiev.unicyb.question.builder.AbstractQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractQuestionFactory {
	AbstractQuestionBuilder questionBuilder;

	public abstract AbstractQuestion getQuestion();

	public AbstractQuestionBuilder getQuestionBuilder() {
		return questionBuilder;
	}

	public void setQuestionBuilder(AbstractQuestionBuilder questionBuilder) {
		this.questionBuilder = questionBuilder;
	}
}
