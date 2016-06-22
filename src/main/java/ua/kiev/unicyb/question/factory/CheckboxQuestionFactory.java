package ua.kiev.unicyb.question.factory;

import ua.kiev.unicyb.question.CheckboxQuestion;
import ua.kiev.unicyb.question.builder.CheckboxQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class CheckboxQuestionFactory extends AbstractQuestionFactory {

	public CheckboxQuestionFactory() {
		questionBuilder = new CheckboxQuestionBuilder();
	}

	@Override
	public CheckboxQuestion getQuestion() {
		return ((CheckboxQuestionBuilder)questionBuilder).build();
	}
}
