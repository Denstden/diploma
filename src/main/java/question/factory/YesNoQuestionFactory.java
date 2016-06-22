package question.factory;

import question.YesNoQuestion;
import question.builder.YesNoQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class YesNoQuestionFactory extends AbstractQuestionFactory {
	public YesNoQuestionFactory() {
		questionBuilder = new YesNoQuestionBuilder();
	}

	@Override
	public YesNoQuestion getQuestion() {
		return ((YesNoQuestionBuilder)questionBuilder).build();
	}
}
