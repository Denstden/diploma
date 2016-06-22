package ua.kiev.unicyb.question.factory;

import ua.kiev.unicyb.question.RadioButtonQuestion;
import ua.kiev.unicyb.question.builder.RadioButtonQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class RadioButtonQuestionFactory extends AbstractQuestionFactory {
	public RadioButtonQuestionFactory() {
		questionBuilder = new RadioButtonQuestionBuilder();
	}

	@Override
	public RadioButtonQuestion getQuestion() {
		return ((RadioButtonQuestionBuilder) questionBuilder).build();
	}
}
