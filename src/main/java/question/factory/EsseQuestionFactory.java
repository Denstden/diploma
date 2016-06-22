package question.factory;

import question.EsseQuestion;
import question.builder.EsseQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class EsseQuestionFactory extends AbstractQuestionFactory {
	@Override
	public EsseQuestion getQuestion() {
		return ((EsseQuestionBuilder)questionBuilder).build();
	}
}
