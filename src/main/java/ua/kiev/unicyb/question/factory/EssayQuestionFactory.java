package ua.kiev.unicyb.question.factory;

import ua.kiev.unicyb.question.EssayQuestion;
import ua.kiev.unicyb.question.builder.EssayQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class EssayQuestionFactory extends AbstractQuestionFactory {

	public EssayQuestionFactory() {
		questionBuilder = new EssayQuestionBuilder();
	}

	@Override
	public EssayQuestion getQuestion() {
		return ((EssayQuestionBuilder)questionBuilder).build();
	}
}
