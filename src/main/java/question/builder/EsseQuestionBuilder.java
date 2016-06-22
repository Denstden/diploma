package question.builder;

import format.FormatSettings;
import format.FormatType;
import question.EsseQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class EsseQuestionBuilder extends AbstractQuestionBuilder {

	@Override
	public EsseQuestion build() {
		EsseQuestion esseQuestion = new EsseQuestion();
		esseQuestion.setQuestion(globalPreambula+" "+configData.getPreambula());
		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		esseQuestion.setFormatSettings(formatSettings);
		return esseQuestion;
	}
}
