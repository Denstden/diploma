package question.factory;

import question.RadioButtonQuestion;
import question.builder.RadioButtonQuestionBuilder;

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
