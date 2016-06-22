package question.factory;

import question.EsseQuestion;
import question.builder.EsseQuestionBuilder;

/**
 * @Author Denys Storozhenko.
 */
public class EsseQuestionFactory extends AbstractQuestionFactory {

	public EsseQuestionFactory() {
		questionBuilder = new EsseQuestionBuilder();
	}

	@Override
	public EsseQuestion getQuestion() {
		return ((EsseQuestionBuilder)questionBuilder).build();
	}
}
