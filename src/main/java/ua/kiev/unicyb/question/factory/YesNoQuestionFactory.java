package ua.kiev.unicyb.question.factory;

import ua.kiev.unicyb.question.YesNoQuestion;
import ua.kiev.unicyb.question.builder.YesNoQuestionBuilder;

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
