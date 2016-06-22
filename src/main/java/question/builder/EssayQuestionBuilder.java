package question.builder;

import question.format.FormatSettings;
import question.format.FormatType;
import question.EssayQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class EssayQuestionBuilder extends AbstractQuestionBuilder {

	@Override
	public EssayQuestion build() {
		EssayQuestion essayQuestion = new EssayQuestion();
		essayQuestion.setQuestion(globalPreamble +" "+configData.getPreamble());
		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		essayQuestion.setFormatSettings(formatSettings);
		return essayQuestion;
	}
}
