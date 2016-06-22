package question.factory;

import question.RadioButtonQuestion;
import question.builder.RadioButtonQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class RadioButtonQuestionFactory extends AbstractQuestionFactory {
	@Override
	public RadioButtonQuestion getQuestion() {
		return ((RadioButtonQuestionBuilder)questionBuilder).build();
	}
}
