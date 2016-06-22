package question.factory;

import question.CheckboxQuestion;
import question.builder.CheckboxQuestionBuilder;

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
