package question.factory;

import question.EssayQuestion;
import question.builder.EssayQuestionBuilder;

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
